package com.biztrace.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.biztrace.web.security.model.Organization;
import com.biztrace.web.security.model.User;

@Service
public class EntityClassMapper {
    private static final Map<String, Class> ENTITY_CLASS_MAP = new HashMap<String, Class>();
    
    static {
        ENTITY_CLASS_MAP.put("Organization", Organization.class);
        ENTITY_CLASS_MAP.put("User", User.class);
    }
    
    public Class getClassByName(final String name) {
        return ENTITY_CLASS_MAP.get(name);
    }
}
