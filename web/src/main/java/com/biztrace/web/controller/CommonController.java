package com.biztrace.web.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biztrace.util.UniquenessValidator;

/**
 * The @RequestParam annotation or un-annotated command objects can be used to bind parameters
 * on an HTTP GET request to controller method parameters.
 * In either case, the Spring DataBinder mechanism is used, which includes support for
 * JSR-303 validation, customizable type conversion, controller-specific data binding
 * customization (e.g. @InitBinder), etc. Most of the features described in the Spring MVC
 * documentation apply to this method of request binding. 
 * 
 * The @RequestBody annotation is used to handle POST requests, where the entire body of
 * the HTTP request is bound to the specified controller method parameter.
 * Spring MessageConverter and OXM is used to unmarshall the request body.
 * The DataBinder mechanism is not used, so many of the features described in the Spring MVC
 * documentation are not supported with POST/ @RequestBody style controllers
 * (like JSR-303 validation, one of the major features I was trying to leverage). 
 * 
 * (DataBinder and MessageConverter) were implemented so differently inside the framework,
 * with very different features available. Most of the relevant code is
 * in org.springframework.web.bind.annotation.support.HandlerMethodInvoker.resolveHandlerArguments()
 * 
 * Please refer to bug in JIRA:
 * http://jira.springframework.org/browse/SPR-6740?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
 */
@Controller
public class CommonController {
    @Autowired
    private UniquenessValidator uniquenessValidator;
	
	@RequestMapping("/uniquenessValidate")
	public @ResponseBody Map<String, ? extends Object> uniquenessValidate(
    	    @RequestParam("entityName") String entityName,
    	    @RequestParam("property") String property,
    	    @RequestParam("propertyValue") Object propertyValue) {
	    return Collections.singletonMap("isUnique",
	        uniquenessValidator.isUnique(entityName, property, propertyValue));
	}

    public void setUniquenessValidator(UniquenessValidator uniquenessValidator) {
        this.uniquenessValidator = uniquenessValidator;
    }
}
