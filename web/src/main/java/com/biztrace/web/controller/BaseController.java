package com.biztrace.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.biztrace.web.security.model.User;

@Controller
public class BaseController {
    @Autowired
    protected User user;

    public void setUser(User user) {
        this.user = user;
    }
    
}
