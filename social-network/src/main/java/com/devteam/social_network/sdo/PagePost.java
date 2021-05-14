package com.devteam.social_network.sdo;

import com.devteam.social_network.domain.Love;
import com.devteam.social_network.domain.Media;
import com.devteam.social_network.domain.PostComment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class PagePost {
    private Long postId;
    private String content;
    private LocalDate postDate;
    private LocalTime postTime;
    private String userEmail;
    private List<PostComment> listPostComment;
    private List<String> listLove;
    private List<Media> listMedia;
    private Long totalPost;
    private Long userId;
}
