package com.biztrace.dao;

import com.biztrace.dao.model.Criteria;
import com.biztrace.dao.model.Order;
import com.biztrace.dao.model.Records;
import com.biztrace.model.GenericEntity;

public interface GenericDAO {
    Records list(final String entityName);
    Records list(final String entityName, final Criteria[] criterias, final Order[] orders);
    Records list(final String entityName, final Criteria[] criterias, final Order[] orders,
        final Integer pageNumber, final Integer recordPerPage);
    GenericEntity getUniqueEntity(final String entityName, final Criteria... criterias);
    GenericEntity getUniqueEntity(final String entityName, final boolean reportError, final Criteria... criterias);
    void save(final String entityName, final GenericEntity entity);
}
