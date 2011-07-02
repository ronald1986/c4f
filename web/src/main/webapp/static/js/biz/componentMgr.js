/**
 * Singleton Implementation
 */
Biz.Util.ComponentManager = (function () {
	var components = new Map();

	return {
		reg : function(type, cmpObj) {
			components.put(type + '_' + cmpObj.id, cmpObj);
		},
		get : function(type, id) {
			return components.get(type + '_' + id);
		},
		cleanAll : function () {
			components.each(function(key, value, isLast) {
				if (value.destroy) {
					value.destroy();
				}
	    	});
			components.removeAll();
		}
	};
})();

Biz.regCmp = Biz.Util.ComponentManager.reg;
Biz.getCmp = Biz.Util.ComponentManager.get;

