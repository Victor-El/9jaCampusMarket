package com.chainremita.a9jacampusmarket.api.sponsored;

import java.util.List;

public class SponsoredItem {
    private String message;

    private String status;

    private List<SponsoredItemData> data;

    public SponsoredItem(String message, String status, List<SponsoredItemData> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SponsoredItemData> getData() {
        return data;
    }

    public void setData(List<SponsoredItemData> data) {
        this.data = data;
    }
}
