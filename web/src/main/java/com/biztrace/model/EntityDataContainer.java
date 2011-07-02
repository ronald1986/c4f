package com.biztrace.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EntityDataContainer {
    private GenericEntity docData = new GenericEntityImpl();
    private String module;

    public GenericEntity getDocData() {
        return docData;
    }
    public void setDocData(final GenericEntity docData) {
        this.docData = docData;
    }
    public String getModule() {
        return module;
    }
    public void setModule(final String module) {
        this.module = module;
    }
    
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
