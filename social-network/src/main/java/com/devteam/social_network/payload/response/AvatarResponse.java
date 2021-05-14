package com.devteam.social_network.payload.response;

public class AvatarResponse {
    private String url;

    public AvatarResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
