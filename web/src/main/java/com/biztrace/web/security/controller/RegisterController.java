package com.biztrace.web.security.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biztrace.web.security.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController {
	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private Validator registerValidator;

    /**
     * @param binder WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registerValidator);
    }

    @RequestMapping("/init")
    public ModelAndView init() {
        ModelAndView register = new ModelAndView();
        register.setViewName("register");
        register.addObject(new User());
        return register;
    }

	@RequestMapping("/submit")
	public ModelAndView submit(@Valid @ModelAttribute("user") User user, BindingResult result) {
		ModelAndView view = new ModelAndView();
		if (result.hasErrors()) {
			view.setViewName("register");
			view.addObject(user);
			view.addObject(result.getAllErrors());
		} else {
			view.setViewName("dashboard");
		}
		return view;
	}

    public void setRegisterValidator(Validator registerValidator) {
        this.registerValidator = registerValidator;
    }
	
}
