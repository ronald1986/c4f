package com.biztrace.dao.model;

import org.apache.commons.lang.StringUtils;


public class Order {
    public static enum ORDER {ASC, DESC};
    
    private String propertyName;
    private ORDER order;
    
    public Order(final String propertyName) {
        this.propertyName = propertyName;
        this.order = ORDER.ASC;
    }
    
    public Order(final String propertyName, final String order) {
        this.propertyName = propertyName;
        if (StringUtils.equalsIgnoreCase(order, "ASC")) {
            this.order = ORDER.ASC;
        } else {
            this.order = ORDER.DESC;
        }
    }

    public Order(final String propertyName, final Order.ORDER order) {
        this.propertyName = propertyName;
        this.order = order;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public ORDER getOrder() {
        return order;
    }

    public void setOrder(ORDER order) {
        this.order = order;
    }
}
