Biz.Grid = {};
Biz.Grid.TYPE = 'grid';
Biz.Grid.TYPE_SERVER = 'server';
Biz.Grid.TYPE_LOCAL = 'local';

Biz.regGrid = function(grid) {
	Biz.regCmp(Biz.Grid.TYPE, grid);
};

Biz.getGrid = function(grid) {
	Biz.getCmp(Biz.Grid.TYPE, grid.id);
};

Biz.Grid.requestBuilder = function(oState, oSelf) {
    // Get states or use defaults
    oState = oState || { pagination: null, sortedBy: null };
    var sort = (oState.sortedBy) ? oState.sortedBy.key : "updateDate";
    var dir = (oState.sortedBy && oState.sortedBy.dir === YAHOO.widget.DataTable.CLASS_DESC) ? "desc" : "asc";
    var recordOffset = (oState.pagination) ? oState.pagination.recordOffset : 1;
    var rowsPerPage = (oState.pagination) ? oState.pagination.rowsPerPage : 5;

    // Build custom request
    return  "orders=" + sort + '^' + dir +
            "&page=" + ((recordOffset / rowsPerPage) + 1) +
            "&rows=" + rowsPerPage;
};

Biz.Grid.createGrid = function(config) {
	YAHOO.util.Event.onAvailable(config.id, function() {
		var gridId = config.id;
		var gridType = config.type; // server or local
		var gridCols = config.columns;

		var gridDataTable;
		var gridColDefs = new Array();
		var gridDataFields = new Array();
		
		for (var i = 0; i < gridCols.length; i++) {
			var gridCol = gridCols[i];
			var gridColDef = {};
			gridColDef.key = gridCol.id;
			gridColDef.label = gridCol.label;
			if (gridCol.editable) {
				if (gridCol.type == 'text') {
					gridColDef.editor = new YAHOO.widget.TextboxCellEditor();
				} else if (gridCol.type == 'date') {
					gridColDef.editor = new YAHOO.widget.DateCellEditor();
					gridColDef.formatter = 'date';
				} else if (gridCol.type == 'textarea') {
					gridColDef.editor = new YAHOO.widget.TextareaCellEditor();
				}
			}
			gridColDefs.push(gridColDef);
			
			var gridDataField = {};
			gridDataField.key = gridCol.id;
			if (gridCol.type == 'date') {
				gridDataField.parser = stringToDate;
			}
			gridDataFields.push(gridDataField);
		}

		// DataSource instance 
		var stringToDate = function(sData) { 
			if (typeof sData == 'undefined' || sData == null || sData.length == 0)  {
				return null;
			}
	        var array = sData.split("-"); 
	        return new Date(array[1] + " " + array[0] + ", " + array[2]); 
	    };

	    var gridConf = {
            paginator : new YAHOO.widget.Paginator({
                rowsPerPage: 5
            }),
            width: "40em",
            height: "400px"
        };

	    var gridDataSource = null;
	    if (gridType == Biz.Grid.TYPE_SERVER) {
	    	gridConf.initialRequest = "page=1&rows=5";
	    	gridConf.dynamicData = true;
	    	gridConf.generateRequest = Biz.Grid.requestBuilder;

	    	gridDataSource = new YAHOO.util.DataSource(Biz.CONTEXT + config.dataSource + '/list?', { 
		        responseType : YAHOO.util.DataSource.TYPE_JSON,
		        responseSchema : { 
		            resultsList : "records", 
		            fields: gridDataFields, 
		            metaFields: { 
		                totalRecords: "totalRecords" // Access to value in the server response 
		            } 
		        }
		    });
	    } else {
	    	gridDataSource = new YAHOO.util.LocalDataSource(Biz.getDS().getValue(config.dataSource));
	    	gridDataSource.responseType = YAHOO.util.XHRDataSource.TYPE_JSARRAY;
	    	gridDataSource.responseSchema = {
	    	    fields: gridDataFields
	    	};
	    }

	    gridDataTable = new YAHOO.widget.ScrollingDataTable(gridId,
				gridColDefs, gridDataSource, gridConf);
	    gridDataTable.id = gridId;
	    gridDataTable.dataSource = config.dataSource;
	    gridDataTable.gridType = config.type;
	    gridDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) { 
		    oPayload.totalRecords = oResponse.meta.totalRecords; 
		    return oPayload; 
		};

		gridDataTable.subscribe("cellClickEvent", gridDataTable.onEventShowCellEditor);
		gridDataTable.subscribe("rowMouseoverEvent", gridDataTable.onEventHighlightRow); 
		gridDataTable.subscribe("rowMouseoutEvent", gridDataTable.onEventUnhighlightRow); 
		gridDataTable.subscribe("rowClickEvent", gridDataTable.onEventSelectRow);
		
		if (gridType == Biz.Grid.TYPE_SERVER) {
			gridDataTable.subscribe("rowClickEvent", function(oArgs) {
				var recordId = gridDataTable.getSelectedRows()[0];
				var record = gridDataTable.getRecord(recordId);
				Biz.Module.loadDocument(record.getData('_id'));
			});
		}

		Biz.regGrid(gridDataTable);
		
		if (config.regInDataSource === true) {
			var clientDs = Biz.getDS();
			clientDs.grids[clientDs.grids.length] = gridDataTable;
		}
	}, this);
};

Biz.Grid.createModuleSearchGrid = function(gridDivId, columns, moduleId) {
	Biz.Grid.createGrid({
		id: gridDivId,
		type: Biz.Grid.TYPE_SERVER,
		dataSource: moduleId ? moduleId : Biz.Module.MODULE_ID,
		columns: columns,
		regInDataSource : false
	});
};

Biz.Grid.createModuleChildGrid = function(gridDivId, childProperty, columns) {
	Biz.Grid.createGrid({
		id: gridDivId,
		type: Biz.Grid.TYPE_LOCAL,
		dataSource: childProperty,
		columns: columns,
		regInDataSource : true
	});
};