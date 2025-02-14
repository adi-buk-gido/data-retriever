package com.example.shield.model.conversation;

import lombok.Data;

@Data
public class Message {

    private User user;

    private String dateTime;

    private String dateTimeUTC;

    private String content;

    private String conversationId;
}
