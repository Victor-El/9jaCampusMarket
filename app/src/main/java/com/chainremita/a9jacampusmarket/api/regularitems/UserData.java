package com.chainremita.a9jacampusmarket.api.regularitems;

public class UserData {
    private String phone;
    private String username;

    public UserData(String phone, String username) {
        this.phone = phone;
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
