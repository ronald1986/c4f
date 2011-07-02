package com.biztrace.dao.model;

import java.util.List;

import com.biztrace.model.GenericEntity;
import com.biztrace.model.GenericEntityImpl;

public class Records extends GenericEntityImpl {
    public Integer getTotalPages() {
        return (Integer) this.get("totalPages");
    }
    public void setTotalPages(Integer totalPages) {
        this.put("totalPages", totalPages);
    }
    public Integer getCurrentPage() {
        return (Integer) this.get("currentPage");
    }
    public void setCurrentPage(Integer currentPage) {
        this.put("currentPage", currentPage);
    }
    public Integer getTotalRecords() {
        return (Integer) this.get("totalRecords");
    }
    public void setTotalRecords(Integer totalRecords) {
        this.put("totalRecords", totalRecords);
    }
    public List<GenericEntity> getRecords() {
        return (List<GenericEntity>) this.get("records");
    }
    public void setRecords(List<GenericEntity> records) {
        this.put("records", records);
    }
}
