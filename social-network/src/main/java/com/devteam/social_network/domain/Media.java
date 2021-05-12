package com.devteam.social_network.domain;

import com.devteam.social_network.composite.MediaCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MEDIA")
@Data
@IdClass(MediaCompositeKey.class)
public class Media {
    @Id
    @Column(name = "CAPTION")
    private String caption;

    @Id
    @Column(name = "POSTID")
    private Long postId;

    @Column(name = "TYPE")
    private String type;
    @Column(name = "FILENAME")
    private String fileName;
}
