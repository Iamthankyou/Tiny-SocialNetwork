package com.devteam.social_network.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "POST")
@Data
public class Post {
    @Column(name = "POSTID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "POSTDATE",columnDefinition = "DATE")
    private LocalDate postDate;
    @Column(name = "POSTTIME",columnDefinition = "TIME")
    private LocalTime postTime;
    @Column(name = "USEREMAIL")
    private String userEmail;
}
