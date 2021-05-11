package com.devteam.social_network.service;

import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PostSdo;

public interface PostServiceCustom {

    public PostSdo post(PostSdi postSdi);
}
