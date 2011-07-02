Biz.Module = {};
Biz.Module.Tabs = null;
Biz.Module.Buttons = null;
Biz.Module.DOC_ID = null;

Biz.Module.assignTabData = function(tab) {
	var clientDs = Biz.getDS();
	if (clientDs != null) {
    	// Assigning normal field value
		var tabEl = $(tab.get('contentEl'));
		tabEl.find('.dataField').each(function(index) {
			var fieldValue = clientDs.getValue(this.id);
			if (typeof fieldValue == 'undefined') {
				fieldValue = '';
			}
			$(this).val(fieldValue);
		});
	}
};

/**
 * Builds the TabView for one module based on its tabs definition.
 * @uiTabs tabs definition.  Array of object with id, label properties
 */
Biz.Module.buildModuleTabs = function(uiTabs) {
	Biz.Module.Tabs = new YAHOO.widget.TabView();
	for (var i = 0; i < uiTabs.length; i++) {
        var tabId = uiTabs[i].id;
		var tab = new YAHOO.widget.Tab({
		    label: uiTabs[i].label,
		    active: i == 0 ? true : false,
		    cacheData: true,
		    dataSrc: Biz.CONTEXT + Biz.Module.MODULE_ID + '/load/tab_' + tabId
		});
        tab.addListener('contentChange', function(e) {
        	Biz.Script.extractScript(e.newValue);

        	Biz.Module.assignTabData(this);
        }, null, tab);

        var tabLi = $(tab.get('labelEl')).parent().parent();
        tabLi.attr('id', 'tab_' + tabId);

		Biz.Module.Tabs.addTab(tab);
	}
	Biz.Module.Tabs.addListener('appendTo', function(e) {
		var tabs = Biz.Module.Tabs.get('tabs');
		for (var i = 0; i < tabs.length; i++) {
			var tabLi = $(tabs[i].get('labelEl')).parent().parent();
			if (tabLi.attr('id') != 'tab_search') {
				tabLi.css('display', 'none');
			}
		}
	});
	$('moduleContent').html('');
	Biz.Module.Tabs.appendTo('moduleContent');
};

/**
 * Basic module button actions.  It takes the button as this reference in it.
 */
Biz.Module.moduleButtonAction = function() {
	var buttonId = this.get('id');
	var actionId = buttonId.substr('btn_'.length);

	if (actionId == 'search') {
		for (var i = 0; i < Biz.Module.Buttons.length; i++) {
			var button  = Biz.Module.Buttons[i];
			if (buttonId !== 'btn_search') {
				$('#' + button.get('id')).css('display', 'none');
			}
		}

		var tabs = Biz.Module.Tabs.get('tabs');
		for (var i = 0; i < tabs.length; i++) {
			var tabLi = $(tabs[i].get('labelEl')).parent().parent();
			if (tabLi.attr('id') == 'tab_search') {
				tabLi.css('display', 'inline');
			} else {
				tabLi.css('display', 'none');
			}
		}
		Biz.Module.Tabs.selectTab(0);
	} else if (actionId == 'save') {
		Biz.Module.saveDocument();
	}
};

/**
 * Builds the Button Bar for one module based on its buttons definition.
 * @buttons buttons definition.  Array of object with id, label properties
 */
Biz.Module.buildModuleButtons = function(buttons) {
	Biz.Module.Buttons = new Array();
	$('moduleButtons').html('');
	for (var i = 0; i < buttons.length; i++) {
		var button = new YAHOO.widget.Button({  
			label: buttons[i].label,  
			id: 'btn_' + buttons[i].id,  
			container: "moduleButtons",
			onclick : {fn: Biz.Module.moduleButtonAction}
		});
		Biz.Module.Buttons.push(button);
	}
};

/**
 * Open one module and build tabs & buttons after selecting in system menu.
 * Module Id need to be provided to retrieve buttons, tabs and other properties.
 * @moduleId module Id
 */
Biz.Module.openModule = function(moduleId) {
    Biz.Module.MODULE_ID = moduleId;
    var moduleProperties = {
    	name : 'Product',
    	buttons : [
    	    {id : 'search',
        	 label : 'Search'
    	    },
    	    {id : 'new',
        	 label : 'New'
            },
            {id : 'save',
             label : 'Save'
            }
        ],
        tabs : [
            {id : 'search',
             label : 'Search'
            },
            {id : 'header',
             label : 'Header'
            }
        ]
    };

    Biz.Util.ComponentManager.cleanAll();

    Biz.Module.MODULE_NAME = moduleProperties.name;
    $('#moduleTitle').html(Biz.Module.MODULE_NAME);
    Biz.Module.buildModuleButtons(moduleProperties.buttons);
    Biz.Module.buildModuleTabs(moduleProperties.tabs);
};

/**
 * Builds system module menu
 */
Biz.Module.buildSystemModuleMenu = function() {
    var order = new Array();
    order.push('seq^ASC');

    $.post(Biz.CONTEXT + 'menu/list',
   		{"orders" : order},
   		function(data) {
   			var moduleMenu = new YAHOO.widget.MenuBar("modules", {
       		    autosubmenudisplay: true ,  
 		        hidedelay: 750,  
 		        lazyload: true, 
 		        effect: {  
       		        effect: YAHOO.widget.ContainerEffect.FADE, 
 		            duration: 0.25 
 		        }  
       		});
       		YAHOO.util.Dom.addClass(moduleMenu.element, "yuimenubarnav"); // Add arrow for submenu dropdown

       		for (var i = 0; i < data.records.length; i++) {
           		var cfg = {};
           		cfg.id = data.records[i].id;
                if (data.records[i].submenu) {
                	cfg.submenu = {};
                	cfg.submenu.id = data.records[i].id;
                	cfg.submenu.itemdata = new Array();
                    for (var j = 0; j < data.records[i].submenu.length; j++) {
                        var childMenu = {};
                        childMenu.id = data.records[i].submenu[j].id;
                        childMenu.text = data.records[i].submenu[j].text;
                        childMenu.url = "javascript:Biz.Module.openModule('" + data.records[i].submenu[j].id + "')";
                        cfg.submenu.itemdata.push(childMenu);
                    }
                }

           		var menuItem = new YAHOO.widget.MenuBarItem(data.records[i].text,
                   	cfg);
           		moduleMenu.addItem(menuItem);
       		}
       		moduleMenu.render('header');
   		},
   		'json');
};

/**
 * Load one document in module search grid
 * @documentId document Id
 */
Biz.Module.loadDocument = function(documentId) {
	Biz.Module.DOC_ID = documentId;
	console.debug(' ---------  loading doc -------- ');
	$.get(Biz.CONTEXT + Biz.Module.MODULE_ID + '/get/' + documentId,
	   	null,
	   	function(data) {
			var clientDs = Biz.getDS();
			if (clientDs) {
				console.debug('coming to reset');
				clientDs.reset(data._id, data);
			} else {
				clientDs = new Biz.Data.ClientDataSource(data._id, Biz.Module.MODULE_ID, data);
			}

			// Show all tabs except tab_search
			var tabs = Biz.Module.Tabs.get('tabs');
			for (var i = 0; i < tabs.length; i++) {
				var tab = tabs[i];
				if (tab.get('dataLoaded') === true) {
					Biz.Module.assignTabData(tab);
				}

				var tabLi = $(tab.get('labelEl')).parent().parent();
				if (tabLi.attr('id') == 'tab_search') {
					tabLi.css('display', 'none');
				} else {
					tabLi.css('display', '');
				}
			}
			
			Biz.Module.Tabs.selectTab(1);

			// Show all buttons
			for (var i = 0; i < Biz.Module.Buttons.length; i++) {
				var button  = Biz.Module.Buttons[i];
				$('#' + button.get('id')).css('display', '');
			}
	   	}
	);
};

/**
 * Save the document
 */
Biz.Module.saveDocument = function() {
	var clientDs = Biz.getDS();
	if (clientDs != null) {
		$.post(Biz.CONTEXT + Biz.Module.MODULE_ID + '/save',
			{docData : YAHOO.lang.JSON.stringify(clientDs.getDocJson())},
		   	function(data) {
				console.debug('after save: ' + data);
		   	}
		);
	}
};

Biz.Module.updateClientDs = function(fieldElm) {
	
};