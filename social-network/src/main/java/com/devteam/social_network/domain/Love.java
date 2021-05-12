package com.devteam.social_network.domain;

import com.devteam.social_network.composite.LoveCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LOVE")
@Data
@IdClass(LoveCompositeKey.class)
public class Love {

    @Id
    @Column(name = "POSTID")
    private Long postId;
    @Id
    @Column(name = "USEREMAIL")
    private String userEmail;
}
