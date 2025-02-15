package com.example.shield.service.kafka;

import org.springframework.stereotype.Service;

import com.example.shield.model.conversation.RoomConversation;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, RoomConversation> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, RoomConversation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDataToEnrichment(RoomConversation data) {
        kafkaTemplate.send("data-enrichment-topic", data);
    }

}
