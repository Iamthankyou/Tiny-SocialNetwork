package com.devteam.social_network.domain;

import com.devteam.social_network.composite.PostCommentCompositeKey;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "POST_COMMENT")
@IdClass(PostCommentCompositeKey.class)
public class PostComment {
    @Id
    @Column(name = "POSTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Id
    @Column(name = "COMMENTTIME")
    private LocalTime commentTime;
    @Id
    @Column(name = "COMMENTDATE")
    private LocalDate commentDate;
    @Id
    @Column(name = "USEREMAIL")
    private String userEmail;
    @Column(name = "CONTENT")
    private String content;
}
