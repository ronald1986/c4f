package com.biztrace.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class BaseControllerTest {
	private HttpServletRequest request = new MockHttpServletRequest();  
    private HttpServletResponse response = new MockHttpServletResponse();  
    private XmlWebApplicationContext context;  
    private MockServletContext msc;  
	
    protected void setUp() throws Exception {  
        String[] contexts = new String[] { "applicationContext.xml" };  
        context = new XmlWebApplicationContext();  
        context.setConfigLocations(contexts);  
        msc = new MockServletContext();  
        context.setServletContext(msc);  
        context.refresh();  
        msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);  
    }  
    
	@Test
	public void testPageNotFoundhandling() {
		
	}
}
