package com.devteam.social_network.domain;

import com.devteam.social_network.composite.MessageCompositeKey;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
@Data
public class Message {

    @Column(name = "THREADID")
    private Long threadId;
    @Id
    @Column(name = "MESSAGEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "SENDER")
    private String sender;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CREATEAT")
    private Date createAt;
    @Column(name = "UPDATEAT")
    private Date updateAt;
    @Column(name = "TYPE")
    private String type;
}
