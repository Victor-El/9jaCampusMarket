package com.chainremita.a9jacampusmarket.api.regularitems;

public class Location {

    private String id;

    private String name;

    private String abbr;

    private String state;

    private String city;

    private String status;

    public Location(String id, String name, String abbr, String state, String city, String status) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.state = state;
        this.city = city;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
