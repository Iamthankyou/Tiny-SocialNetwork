package com.devteam.social_network.sdo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostCustomSdo implements Serializable {
    private Long postId;
    private String content;
    private Time postTime;
    private Date postDate;
    private String userEmail;
    private String reactionType;

    @Override
    public String toString() {
        return "PostCustomSdo{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", postDate=" + postDate +
                ", userEmail='" + userEmail + '\'' +
                ", reactionType='" + reactionType + '\'' +
                '}';
    }
}
