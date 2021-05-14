package com.devteam.social_network.domain;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE_THREAD")
@Data
public class MessageThread {

    @Id
    @Column(name = "THREADID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;
    @Column(name = "OWNEREMAIL")
    private String ownerEmail;
    @Column(name = "CREATEAT")
    private Date createAt;
    @Column(name = "UPDATEAT")
    private Date updateAt;
}
