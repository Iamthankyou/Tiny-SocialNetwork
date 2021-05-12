package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.List;

@Data
public class PageableSdo {
    private List<PagePost> listPagePost;
    private Long totalPost;
}
