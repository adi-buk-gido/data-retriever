package com.example.shield.model.conversation;

import lombok.Data;
import java.util.List;

@Data
public class Conversation {

    private String roomId;

    private String startTime;

    private String startTimeUTC;

    private String endTime;

    private String endTimeUTC;

    private List<Participant> participantsEntered;

    private List<Participant> participantsLeft;

    private List<Message> messages;

    private List<Attachment> attachments;
}
