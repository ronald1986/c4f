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

import com.biztrace.web.controller.BaseController;
import com.biztrace.web.security.model.LoginUser;

@Controller
public class LoginController extends BaseController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private Validator loginValidator;

	/**
	 * @param binder WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}

    /**
     * Custom handler for the home view.
     * <p>
     * Note that this handler relies on the RequestToViewNameTranslator to
     * determine the logical view name based on the request URL: "/home.do"
     * -&gt; "home".
     */
    @RequestMapping("/")
    public ModelAndView home() {
        System.out.println(user);
        ModelAndView home = new ModelAndView();
        home.setViewName("home");
        home.addObject(new LoginUser());
        return home;
    }

	@RequestMapping("/login")
	public ModelAndView login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result) {
		ModelAndView view = new ModelAndView();
		if (result.hasErrors()) {
			view.setViewName("home");
			view.addObject(loginUser);
			view.addObject(result.getAllErrors());
		} else {
		    user.setLoginId(loginUser.getLoginId());
			view.setViewName("dashboard");
		}
		return view;
	}
}
