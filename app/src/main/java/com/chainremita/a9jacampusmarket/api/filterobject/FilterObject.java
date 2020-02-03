package com.chainremita.a9jacampusmarket.api.filterobject;

import com.google.gson.annotations.SerializedName;

public class FilterObject {
    @SerializedName("price1")
    private String minimumPrice;
    @SerializedName("price2")
    private String maximumPrice;
    @SerializedName("location_abbr")
    private String location;
    @SerializedName("category_id")
    private String category;
    @SerializedName("date1")
    private String startDate;
    @SerializedName("date2")
    private String endDate;
    private String username;

    public FilterObject(String minimumPrice, String maximumPrice, String location, String category, String startDate, String endDate, String username) {
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
        this.location = location;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(String maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
