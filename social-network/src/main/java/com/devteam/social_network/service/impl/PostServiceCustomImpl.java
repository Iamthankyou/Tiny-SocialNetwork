package com.devteam.social_network.service.impl;

import com.devteam.social_network.common.exception.AppException;
import com.devteam.social_network.domain.*;
import com.devteam.social_network.repos.PostRepoService;
import com.devteam.social_network.sdi.PostSdi;
import com.devteam.social_network.sdo.PagePost;
import com.devteam.social_network.sdo.PageableSdo;
import com.devteam.social_network.sdo.PostCustomSdo;
import com.devteam.social_network.sdo.PostSdo;
import com.devteam.social_network.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    PostCommentService postCommentService;
    @Autowired
    LoveService loveService;
    @Autowired
    AccountService accountService;
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
        postSdo.setPostDate(postOut.getPostDate());
        postSdo.setPostTime(postOut.getPostTime());
        postSdo.setUserEmail(postOut.getUserEmail());
        postSdo.setReactionType(postReactionOut.getReationType());
        return postSdo;
    }

    @Override
    public void deletePost(Long postId) {
        postService.deleteById(postId);
    }

    @Override
    public Page<PostCustomSdo> listPost(Pageable pageable) {
        return postRepoService.listPost(pageable);
    }

    @Override
    public PageableSdo listPostVer2(int pageIndex, int size) {
        PageableSdo pageableSdo = new PageableSdo();
        List<PagePost> result = new ArrayList<>();
        List<Post> list = postService.findAll(PageRequest.of(pageIndex,size,Sort.by("postDate").descending().and(Sort.by("postTime")))).toList();
        list.forEach(postOut -> {
            PagePost pagePost = new PagePost();
            pagePost.setPostId(postOut.getPostId());
            pagePost.setContent(postOut.getContent());
            pagePost.setPostDate(postOut.getPostDate());
            pagePost.setPostTime(postOut.getPostTime());
            pagePost.setUserEmail(postOut.getUserEmail());
            pagePost.setListPostComment(postCommentService
                    .findAll().stream()
                    .filter(pc -> pc.getPostId() == postOut.getPostId())
                    .sorted(((o1, o2) -> {
                        if (o1.getCommentDate().isAfter(o2.getCommentDate())){
                            return -1;
                        }
                        if (o1.getCommentDate().isBefore(o2.getCommentDate())){
                            return 1;
                        }
                        if (o1.getCommentTime().isAfter(o2.getCommentTime())){
                            return -1;
                        }
                        if (o1.getCommentTime().isBefore(o2.getCommentTime())){
                            return 1;
                        }
                        return 0;
                    }))
                    .collect(Collectors.toList()));
            pagePost.setListLove(loveService.findAll().stream().filter(l -> l.getPostId() == postOut.getPostId()).map(l -> l.getUserEmail()).collect(Collectors.toList()));
            pagePost.setListMedia(mediaService.findAll().stream().filter(m -> m.getPostId() == postOut.getPostId()).collect(Collectors.toList()));
            result.add(pagePost);
        });
        result.sort((o1, o2) -> {
            if (o1.getPostDate().isAfter(o2.getPostDate())){
                return -1;
            }
            if (o1.getPostDate().isBefore(o2.getPostDate())){
                return 1;
            }
            if (o1.getPostTime().isAfter(o2.getPostTime())){
                return -1;
            }
            if (o1.getPostTime().isBefore(o2.getPostTime())){
                return 1;
            }
            return 0;
        });
        pageableSdo.setListPagePost(result);
        pageableSdo.setTotalPost(postService.findAll().stream().count());
        return pageableSdo;
    }


}
