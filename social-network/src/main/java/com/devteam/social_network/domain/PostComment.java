package com.devteam.social_network.domain;

import com.devteam.social_network.composite.PostCommentCompositeKey;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "POST_COMMENT")
public class PostComment {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "POSTID")
    private Long postId;
    @Column(name = "COMMENTTIME",columnDefinition = "TIME")
    private LocalTime commentTime;
    @Column(name = "COMMENTDATE",columnDefinition = "DATE")
    private LocalDate commentDate;
    @Column(name = "USEREMAIL")
    private String userEmail;
    @Column(name = "CONTENT")
    private String content;
}
