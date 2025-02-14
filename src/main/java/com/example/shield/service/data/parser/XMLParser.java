package com.example.shield.service.data.parser;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import com.example.shield.model.conversation.RoomConversation;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class XMLParser implements IDataParser {

    @Override
    public RoomConversation parseData(InputStream fileStream) {
        try {
            log.info("Parsing XML data...");
            JAXBContext context = JAXBContext.newInstance(RoomConversation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (RoomConversation) unmarshaller.unmarshal(fileStream);
        } catch (JAXBException e) {
            log.error("Error parsing XML", e);
            throw new RuntimeException("Failed to parse XML", e);
        }
    }

}
