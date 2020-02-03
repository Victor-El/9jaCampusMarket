package com.chainremita.a9jacampusmarket.api.filterobject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FCategory {
    private String status;
    private String message;
    @SerializedName("data")
    private List<FilterCategory> filterCategoryList;

    public FCategory(String status, String message, List<FilterCategory> filterCategoryList) {
        this.status = status;
        this.message = message;
        this.filterCategoryList = filterCategoryList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FilterCategory> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<FilterCategory> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }
}
