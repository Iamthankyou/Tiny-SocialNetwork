package com.devteam.social_network.domain;

import com.devteam.social_network.composite.FriendCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND")
@Data
@IdClass(FriendCompositeKey.class)
public class Friend {
    @Id
    @Column(name = "USEREMAIL")
    private String userEmail;
    @Id
    @Column(name = "FRIENDEMAIL")
    private String followEmail;
}
