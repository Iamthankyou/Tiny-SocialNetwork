package com.devteam.social_network.controller;


import com.devteam.social_network.sdi.MessageSdi;
import com.devteam.social_network.sdo.MessageSdo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public MessageSdo send(MessageSdi messageSdi) throws Exception{
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        MessageSdo messageSdo = new MessageSdo();
        messageSdo.setFrom(messageSdi.getFrom());
        messageSdo.setText(messageSdi.getText());
        messageSdo.setTime(time);

        return messageSdo;
    }
}
