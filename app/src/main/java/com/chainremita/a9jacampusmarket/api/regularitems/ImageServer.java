package com.chainremita.a9jacampusmarket.api.regularitems;

import com.google.gson.annotations.SerializedName;

public class ImageServer {

    @SerializedName("server_id")
    private String serverId;

    public ImageServer(String serverId) {
        this.serverId = serverId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
