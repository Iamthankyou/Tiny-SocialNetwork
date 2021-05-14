package com.devteam.social_network.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Data
public class Account {
    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "NICKNAME")
    private String nickName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "AVATAR")
    private String avatar;
}
