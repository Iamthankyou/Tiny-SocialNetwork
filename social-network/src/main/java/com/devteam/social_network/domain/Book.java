package com.devteam.social_network.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ISBN")
    private String ISBN;
    @Column(name = "IMAGE_BOOK")
    private String imageBook;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLISH")
    private String publish;
    @Column(name = "WIDTH")
    private Float width;
    @Column(name = "HEIGHT")
    private Float height;
    @Column(name = "PAGE_NUMBER")
    private Integer pageNumber;
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "DISCOUNT")
    private Float discount;
    @Column(name = "MASS")
    private Float mass;
    @Column(name = "TYPE")
    private String type;
}
