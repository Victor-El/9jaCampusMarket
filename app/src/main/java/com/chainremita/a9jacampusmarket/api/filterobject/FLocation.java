package com.chainremita.a9jacampusmarket.api.filterobject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FLocation {
    private String status;
    private String message;
    @SerializedName("data")
    private List<FilterLocation> filterLocationList;

    public FLocation(String status, String message, List<FilterLocation> filterLocationList) {
        this.status = status;
        this.message = message;
        this.filterLocationList = filterLocationList;
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

    public List<FilterLocation> getFilterLocationList() {
        return filterLocationList;
    }

    public void setFilterLocationList(List<FilterLocation> filterLocationList) {
        this.filterLocationList = filterLocationList;
    }
}
