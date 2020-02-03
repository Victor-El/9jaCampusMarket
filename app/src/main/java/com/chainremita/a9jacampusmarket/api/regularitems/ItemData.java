package com.chainremita.a9jacampusmarket.api.regularitems;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemData {
    private String id;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("location_id")
    private String locationId;

    private String caption;

    @SerializedName("new")
    private String neww;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String price;

    private String discount;

    private String promote;

    private String deposit;

    private String consumed;

    private String description;

    @SerializedName("time_created")
    private String timeCreated;

    @SerializedName("last_modified")
    private String lastModified;

    private Category category;

    @SerializedName("image_server")
    private ImageServer imageServer;

    private Location location;

    @SerializedName("user_data")
    private UserData userData;

    @SerializedName("user_profile")
    private UserProfile userProfile;

    @SerializedName("user_review")
    private boolean userReview;

    @SerializedName("user_rating")
    private UserRating userRating;

    @SerializedName("like_no")
    private LikeNo likeNo;

    private List<Like> likes;

    @SerializedName("image1_server")
    private ImageServer imageServer1;

    @SerializedName("image2_server")
    private ImageServer imageServer2;

    @SerializedName("image3_server")
    private ImageServer imageServer3;

    @SerializedName("image4_server")
    private ImageServer imageServer4;

    public ItemData(String id, String categoryId, String userId, String locationId, String caption, String neww, String image1, String image2, String image3, String image4, String price, String discount, String promote, String deposit, String consumed, String description, String timeCreated, String lastModified, Category category, ImageServer imageServer, Location location, UserData userData, UserProfile userProfile, boolean userReview, UserRating userRating, LikeNo likeNo, List<Like> likes, ImageServer imageServer1, ImageServer imageServer2, ImageServer imageServer3, ImageServer imageServer4) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.locationId = locationId;
        this.caption = caption;
        this.neww = neww;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.price = price;
        this.discount = discount;
        this.promote = promote;
        this.deposit = deposit;
        this.consumed = consumed;
        this.description = description;
        this.timeCreated = timeCreated;
        this.lastModified = lastModified;
        this.category = category;
        this.imageServer = imageServer;
        this.location = location;
        this.userData = userData;
        this.userProfile = userProfile;
        this.userReview = userReview;
        this.userRating = userRating;
        this.likeNo = likeNo;
        this.likes = likes;
        this.imageServer1 = imageServer1;
        this.imageServer2 = imageServer2;
        this.imageServer3 = imageServer3;
        this.imageServer4 = imageServer4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getNeww() {
        return neww;
    }

    public void setNeww(String neww) {
        this.neww = neww;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPromote() {
        return promote;
    }

    public void setPromote(String promote) {
        this.promote = promote;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getConsumed() {
        return consumed;
    }

    public void setConsumed(String consumed) {
        this.consumed = consumed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ImageServer getImageServer() {
        return imageServer;
    }

    public void setImageServer(ImageServer imageServer) {
        this.imageServer = imageServer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isUserReview() {
        return userReview;
    }

    public void setUserReview(boolean userReview) {
        this.userReview = userReview;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public LikeNo getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(LikeNo likeNo) {
        this.likeNo = likeNo;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public ImageServer getImageServer1() {
        return imageServer1;
    }

    public void setImageServer1(ImageServer imageServer1) {
        this.imageServer1 = imageServer1;
    }

    public ImageServer getImageServer2() {
        return imageServer2;
    }

    public void setImageServer2(ImageServer imageServer2) {
        this.imageServer2 = imageServer2;
    }

    public ImageServer getImageServer3() {
        return imageServer3;
    }

    public void setImageServer3(ImageServer imageServer3) {
        this.imageServer3 = imageServer3;
    }

    public ImageServer getImageServer4() {
        return imageServer4;
    }

    public void setImageServer4(ImageServer imageServer4) {
        this.imageServer4 = imageServer4;
    }
}
