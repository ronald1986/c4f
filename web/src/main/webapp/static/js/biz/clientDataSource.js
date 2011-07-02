Biz.Data = {};

Biz.Data.ClientDataSource = function(id, moduleId, docJson) {
    this.id = moduleId;
    this.originalDocJson = $.extend({}, docJson);
    //this.docJson = docJson;
    this.docJson = new Map();
    for (key in docJson) {
    	this.docJson.put(key, docJson[key]);
    }
    this.modified = false;
    this.grids = new Array();

    Biz.regCmp('CDS', this);
};

Biz.Data.ClientDataSource.TYPE = 'CDS';

Biz.Data.ClientDataSource.prototype.getDocJson = function() {
	return this.docJson;
};

Biz.Data.ClientDataSource.prototype.getValue = function(field) {
	/*
	if (this.docJson == null) {
		return null;
	}
	var result = jsonPath(this.docJson, '$.' + field);
	if (result == false) {
		return null;
	}
	return result[0];
	*/
	return this.docJson.get(field);
};

Biz.Data.ClientDataSource.prototype.setValue = function(field, value) {
	this.modified = true;
	/*
	var jsonPath = 'this.modifiedJson.' + field;
	eval(jsonPath + '= value');
	*/
	this.docJson.put(field, value);
};

/**
 * Reset data for another document in the same module
 * @return
 */
Biz.Data.ClientDataSource.prototype.reset = function(id, docJson) {
	// Resetting properties
	this.originalDocJson = $.extend({}, docJson);
    this.docJson.removeAll();
    for (key in docJson) {
    	this.docJson.put(key, docJson[key]);
    }
    this.modified = false;

	// Resetting child grids
	for (var i = 0; i < this.grids.length; i++) {
		var childGrid = this.grids[i];
		var fieldValue = Biz.getDS().getValue(childGrid.dataSource);
		childGrid.deleteRows(0, childGrid.getRecordSet().getLength());
		childGrid.addRows(fieldValue);
	}
};

Biz.getDS = function() {
	return Biz.getCmp(Biz.Data.ClientDataSource.TYPE, Biz.Module.MODULE_ID);
};

