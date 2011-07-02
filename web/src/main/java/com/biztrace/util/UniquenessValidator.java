package com.biztrace.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biztrace.dao.GenericDAO;
import com.biztrace.dao.model.Criteria;

@Component
public class UniquenessValidator {
    @Autowired
    private GenericDAO genericDAO;
    
    public boolean isUnique(final String entityName, final String propertyName,
            final Object propertyValue) {

        return genericDAO.getUniqueEntity(entityName, new Criteria(propertyName, propertyValue)) == null;
    }

    public boolean isUnique(final String entityName, final Criteria... criterias) {
        return genericDAO.getUniqueEntity(entityName, criterias) == null;
    }

    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }
}
