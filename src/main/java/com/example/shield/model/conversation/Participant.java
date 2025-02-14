package com.example.shield.model.conversation;

import lombok.Data;

@Data
public class Participant {

    private User user;

    private String dateTime;

    private String dateTimeUTC;

    private String conversationId;
}
