package com.devteam.social_network.service;

import com.devteam.social_network.domain.Post;
import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PostCustomSdo;
import com.devteam.social_network.sdo.PostSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostServiceCustom {

    public PostSdo post(PostSdi postSdi);

    public void deletePost(Long postId);
    public Page<PostCustomSdo> listPost(Pageable pageable);

    public Page<Post> listPostVer2(int pageIndex,int size);
}
