package com.chainremita.a9jacampusmarket.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class AppItemDataResult {
    public int id;

    private String caption;
    private String category;
    private String phone;

    @ColumnInfo(name = "class")
    private String klass;

    private String negotiable;
    private String price;

    @ColumnInfo(name = "seller_username")
    private String sellerUsername;

    @ColumnInfo(name = "seller_rating")
    private String sellerRating;

    @ColumnInfo(name = "location_name")
    private String locationName;

    @ColumnInfo(name = "location_abbr")
    private String locationAbbr;
    private String description;
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
/*

    @ColumnInfo(name = "reviewer_name")
    private String reviewerName;

    @ColumnInfo(name = "reviewer_username")
    private String reviewerUsername;
    private String date;
    private String review;*/

    public AppItemDataResult(int id, String caption, String category, String phone, String klass, String negotiable, String price, String sellerUsername, String sellerRating, String locationName, String locationAbbr, String description, String image, String image1, String image2, String image3, String image4) {
        this.id = id;
        this.caption = caption;
        this.category = category;
        this.phone = phone;
        this.klass = klass;
        this.negotiable = negotiable;
        this.price = price;
        this.sellerUsername = sellerUsername;
        this.sellerRating = sellerRating;
        this.locationName = locationName;
        this.locationAbbr = locationAbbr;
        this.description = description;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        /*this.reviewerName = reviewerName;
        this.reviewerUsername = reviewerUsername;
        this.date = date;
        this.review = review;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(String negotiable) {
        this.negotiable = negotiable;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAbbr() {
        return locationAbbr;
    }

    public void setLocationAbbr(String locationAbbr) {
        this.locationAbbr = locationAbbr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    /*public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }*/
}
