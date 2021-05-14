package com.devteam.social_network.sdo;

import lombok.Data;

import java.util.List;

@Data
public class ListMessageInfoSdo {
    private List<MessageInfoSdo> messageInfoSdoList;
    private int total;
}
