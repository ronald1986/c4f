package com.biztrace.web.security.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.biztrace.web.security.model.User;

@Component
public class RegisterValidator implements Validator {
    private Logger logger = LoggerFactory.getLogger(RegisterValidator.class);

    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
    }

}
