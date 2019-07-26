package com.aatapi.datatransferobject;

import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * A DTO class which is used as a form object in the search form.
 */
public class SearchDTO {

    //map of search params
    private Map params = new HashMap();

    public SearchDTO() {

    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
