package com.example.shield.service.data.parser;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.example.shield.model.conversation.RoomConversation;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JsonParser implements IDataParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public RoomConversation parseData(InputStream fileStream) {
        try {
            return objectMapper.readValue(fileStream, RoomConversation.class);
        } catch (IOException e) {
            log.error("Error parsing JSON data", e);
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

}
