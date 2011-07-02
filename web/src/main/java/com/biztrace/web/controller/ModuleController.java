package com.biztrace.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biztrace.dao.GenericDAO;
import com.biztrace.dao.model.Criteria;
import com.biztrace.dao.model.Order;
import com.biztrace.exception.BusinessException;
import com.biztrace.model.GenericEntity;
import com.biztrace.model.GenericEntityImpl;

@Controller
public class ModuleController {
    @Autowired
    private GenericDAO genericDAO;

    @RequestMapping("/{module}/load/tab_{tabId}")
    public ModelAndView moduleTabLoad(@PathVariable String module, @PathVariable String tabId) {
        ModelAndView moduleTab = new ModelAndView();
        moduleTab.setViewName(module + "/tab_" + tabId);
        return moduleTab;
    }

    @RequestMapping("/{module}/list")
    public @ResponseBody Map<String, ? extends Object> list(@PathVariable String module,
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "rows", required = false) Integer recordPerPage) {
        Criteria[] criterias = null;
        Order[] orders = null;

        List<String> criteria = params.get("criterias[]");
        List<String> order = params.get("orders[]");
        if (criteria != null) {
            criterias = new Criteria[criteria.size()];
            int i = 0;
            for (String key : criteria) {
                criterias[i] = new Criteria(StringUtils.substringBefore(key, "^"),
                    StringUtils.substringAfter(key, "^"));
                i++;
            }
        }
        if (order != null) {
            orders = new Order[order.size()];
            int i = 0;
            for (String key : order) {
                orders[i] = new Order(StringUtils.substringBefore(key, "^"),
                    StringUtils.substringAfter(key, "^"));
                i++;
            }
        }

        return genericDAO.list(module, criterias, orders, pageNumber, recordPerPage);
    }

    @RequestMapping("/{module}/get/{docId}")
    public @ResponseBody Map<String, ? extends Object> get(@PathVariable String module,
            @PathVariable String docId) {
        Criteria criteria = new Criteria("_id", docId);
        GenericEntity entity = genericDAO.getUniqueEntity(module, true, criteria);
        if (entity == null) {
            throw new BusinessException(1000005);
        }
        return entity;
    }
    
    @RequestMapping("/{module}/save")
    public @ResponseBody Map<String, ? extends Object> save(@PathVariable String module,
            @RequestParam String docData) {
        GenericEntity entity = null;
        try {
            entity = new ObjectMapper().readValue(docData, GenericEntityImpl.class);
        } catch (JsonMappingException e) {
            throw new BusinessException(100000601L, e);
        } catch (JsonParseException e) {
            throw new BusinessException(100000602L, e);
        } catch (IOException e) {
            throw new BusinessException(100000603L, e);
        }
        genericDAO.save(module, entity);
        return entity;
    }

    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }
    
}
