package com.devteam.social_network.domain;

import com.devteam.social_network.composite.PostReactionCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "POST_REACTION")
@Data
@IdClass(PostReactionCompositeKey.class)
public class PostReaction {
    @Id
    @Column(name = "USEREMAIL")
    private String userEmail;
    @Id
    @Column(name = "POSTID")
    private Long postId;
    @Column(name = "REACTIONTYPE")
    private String reationType;
}
