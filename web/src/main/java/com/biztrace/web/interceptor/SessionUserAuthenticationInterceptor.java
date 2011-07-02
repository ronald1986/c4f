package com.biztrace.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.biztrace.exception.BusinessException;
import com.biztrace.web.security.model.User;

@Component
public class SessionUserAuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected User user;

    public boolean preHandle(final HttpServletRequest request,
        final HttpServletResponse response, final Object handler) throws Exception {

        if (StringUtils.isEmpty(user.getLoginId())) {
            throw new BusinessException(1000004L);
//            response.sendRedirect("http://host.com/outsideOfficeHours.html");
//            return false;
        }
        return true;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
