package com.devteam.social_network.sdo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MessageSdo {
    private String from;
    private String text;
    private LocalDate date;
    private LocalTime time;
    private Long postId;
}
