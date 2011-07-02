package com.biztrace.web.security.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.biztrace.web.security.PasswordManager;
import com.biztrace.web.security.controller.LoginController;
import com.biztrace.web.security.model.LoginUser;

@Component
public class LoginValidator extends LocalValidatorFactoryBean {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private PasswordManager passwordManager;

	public boolean supports(Class<?> clazz) {
		return LoginUser.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgCode", "orgCode.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "loginId.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
	    super.validate(obj, errors);

		LoginUser user = (LoginUser) obj;
		if (errors.getErrorCount() == 0 && !passwordManager.isPasswordValid(
				user.getOrgCode(), user.getLoginId(), user.getPassword())) {
			errors.rejectValue("password", "invalidLogin");
		}
	}

	public void setPasswordManager(PasswordManager passwordManager) {
		this.passwordManager = passwordManager;
	}
	
}
