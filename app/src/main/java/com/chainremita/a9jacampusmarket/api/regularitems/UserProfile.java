package com.chainremita.a9jacampusmarket.api.regularitems;

import com.google.gson.annotations.SerializedName;

public class UserProfile {
    private String id;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("f_name")
    private String firstName;

    @SerializedName("l_name")
    private String lastName;

    @SerializedName("o_name")
    private String otherName;

    @SerializedName("profile_image")
    private String profileImage;
    private String dob;
    private String gender;
    private String address;

    @SerializedName("location_id")
    private String locationId;

    private String lastModified;

    private String status;

    public UserProfile(String id, String userId, String firstName, String lastName, String otherName, String profileImage, String dob, String gender, String address, String locationId, String lastModified, String status) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.profileImage = profileImage;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.locationId = locationId;
        this.lastModified = lastModified;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
