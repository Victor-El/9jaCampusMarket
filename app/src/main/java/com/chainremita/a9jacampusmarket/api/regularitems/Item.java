package com.chainremita.a9jacampusmarket.api.regularitems;

import java.util.List;

public class Item {
    private String status;
    private String message;

    private List<ItemData> data;

    public Item(String status, String message, List<ItemData> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<ItemData> getData() {
        return data;
    }

    public void setData(List<ItemData> data) {
        this.data = data;
    }
}
