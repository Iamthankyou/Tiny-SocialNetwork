package com.devteam.social_network.sdo;

import lombok.Data;

@Data
public class TopTenBookSdo {
    private String isbn;
    private Long amount;
    private String author;
    private Float discount;
    private Float height;
    private String imageBook;
    private Float mass;
    private String name;
    private Long pageNumber;
    private Float price;
    private String publish;
    private String type;
    private Float width;
}
