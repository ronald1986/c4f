package com.biztrace.web.security;

import org.springframework.stereotype.Service;

import com.biztrace.model.GenericEntity;

@Service
public class UserManager {
    public String getUserSystemName(final GenericEntity user) {
        if (user == null) {
            return null;
        }
        return user.get("loginId") + "|" + user.get("orgCode");
    }
}
