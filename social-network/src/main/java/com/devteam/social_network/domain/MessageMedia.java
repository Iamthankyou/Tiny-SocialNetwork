package com.devteam.social_network.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGEMEDIA")
@Data
public class MessageMedia {
    @Id
    @Column(name = "MESSAGEMEDIAID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageMediaId;
    @Column(name = "FILENAME")
    private String filename;
}
