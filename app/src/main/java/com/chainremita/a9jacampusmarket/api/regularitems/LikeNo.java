package com.chainremita.a9jacampusmarket.api.regularitems;

import com.google.gson.annotations.SerializedName;

public class LikeNo {

    @SerializedName("like_no")
    private String numberOfLikes;

    public LikeNo(String numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(String numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
}
