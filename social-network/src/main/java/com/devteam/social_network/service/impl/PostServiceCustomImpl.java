package com.devteam.social_network.service.impl;

import com.devteam.social_network.common.exception.AppException;
import com.devteam.social_network.domain.Post;
import com.devteam.social_network.domain.PostReaction;
import com.devteam.social_network.repos.PostRepoService;
import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PostCustomSdo;
import com.devteam.social_network.sdo.PostSdo;
import com.devteam.social_network.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PostServiceCustomImpl implements PostServiceCustom {

    @Autowired
    AccountServiceCustom accountServiceCustom;
    @Autowired
    PostService postService;
    @Autowired
    MediaService mediaService;
    @Autowired
    PostReactionService postReactionService;
    @Autowired
    PostRepoService postRepoService;
    @Override
    public PostSdo post(PostSdi postSdi) {
        if (accountServiceCustom.getAccountByEmail(postSdi.getUserEmail()) == null){
            throw new AppException("API00","Account not exist");
        }
        if (postSdi.getContent() == null){
            throw new AppException("API01","Content not null");
        }
        if (postSdi.getContent()!=null && postSdi.getContent().trim() == ""){
            throw new AppException("API02","Content not empty");
        }
        // Tao moi 1 post
        Post post = new Post();
        post.setContent(postSdi.getContent());
        post.setUserEmail(postSdi.getUserEmail());
        post.setPostDate(LocalDate.now());
        post.setPostTime(LocalTime.now());
        Post postOut = postService.save(post);

        // Tao moi 1 Post Reaction

        PostReaction postReaction = new PostReaction();
        postReaction.setPostId(postOut.getPostId());
        postReaction.setReationType(postSdi.getReactionType());
        postReaction.setUserEmail(postSdi.getUserEmail());

        PostReaction postReactionOut = postReactionService.save(postReaction);

        PostSdo postSdo = new PostSdo();
        postSdo.setPostId(postOut.getPostId());
        postSdo.setContent(postOut.getContent());
        postSdo.setLocalDate(postOut.getPostDate());
        postSdo.setLocalTime(postOut.getPostTime());
        postSdo.setUserEmail(postOut.getUserEmail());
        postSdo.setReactionType(postReactionOut.getReationType());
        return postSdo;
    }

    @Override
    public Page<PostCustomSdo> listPost(Pageable pageable) {
        return postRepoService.listPost(pageable);
    }

    @Override
    public Page<Post> listPostVer2(int pageIndex, int size) {
        return postService.findAll(PageRequest.of(pageIndex,size));
    }


}
