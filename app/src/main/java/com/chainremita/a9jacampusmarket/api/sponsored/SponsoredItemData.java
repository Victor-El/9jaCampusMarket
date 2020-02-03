package com.chainremita.a9jacampusmarket.api.sponsored;

import com.chainremita.a9jacampusmarket.api.regularitems.ImageServer;
import com.chainremita.a9jacampusmarket.api.regularitems.Like;
import com.chainremita.a9jacampusmarket.api.regularitems.LikeNo;
import com.chainremita.a9jacampusmarket.api.regularitems.Location;
import com.chainremita.a9jacampusmarket.api.regularitems.UserData;
import com.chainremita.a9jacampusmarket.api.regularitems.UserProfile;
import com.chainremita.a9jacampusmarket.api.regularitems.UserRating;
import com.chainremita.a9jacampusmarket.api.regularitems.UserReview;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponsoredItemData {

    private String id;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("location_id")
    private String locationId;

    private String caption;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String description;

    private String promote;

    private String deposit;

    private String consumed;

    @SerializedName("time_created")
    private String timeCreated;

    @SerializedName("last_modified")
    private String lastModified;

    private String status;

    private Location location;

    @SerializedName("image_server")
    private ImageServer imageServer;

    @SerializedName("user_data")
    private UserData userData;

    @SerializedName("user_profile")
    private UserProfile userProfile;

    @SerializedName("like_no")
    private LikeNo likeNo;

    private List<Like> likes;

    @SerializedName("user_rating")
    private UserRating userRating;

    public SponsoredItemData(String id, String categoryId, String userId, String locationId, String caption, String image1, String image2, String image3, String image4, String description, String promote, String deposit, String consumed, String timeCreated, String lastModified, String status, Location location, ImageServer imageServer, UserData userData, UserProfile userProfile, LikeNo likeNo, List<Like> likes, UserRating userRating) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.locationId = locationId;
        this.caption = caption;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.description = description;
        this.promote = promote;
        this.deposit = deposit;
        this.consumed = consumed;
        this.timeCreated = timeCreated;
        this.lastModified = lastModified;
        this.status = status;
        this.location = location;
        this.imageServer = imageServer;
        this.userData = userData;
        this.userProfile = userProfile;
        this.likeNo = likeNo;
        this.likes = likes;
        this.userRating = userRating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ImageServer getImageServer() {
        return imageServer;
    }

    public void setImageServer(ImageServer imageServer) {
        this.imageServer = imageServer;
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

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }
}


