package com.example.shield.service.data.retrieval;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.shield.dao.DataFilesDaoImp;
import com.example.shield.model.conversation.RoomConversation;
import com.example.shield.model.file.FileProcessStatus;
import com.example.shield.model.file.InputMetadata;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataRetrievalManager {

    private Map<UUID, RoomConversation> conversationsDB = new HashMap<>();
    
    @Autowired
    DataRetrievalServiceFactory dataRetrievalServiceFactory;

    @Autowired
    DataFilesDaoImp dataFilesDaoImp;




    public void processData(InputMetadata inputMetadata){
        IDataRetrievalService service = DataRetrievalServiceFactory.getService(inputMetadata.getSourceType());
        boolean isProcessed = validateNotProcessed(inputMetadata.getInputId());
        if(!isProcessed){
            log.debug("Processing file: {}", inputMetadata);
            log.debug("Setting process status to initiated for file: {}", inputMetadata);
            setFileStatus(inputMetadata.getInputId(), FileProcessStatus.INITIATED);
            log.debug("Connecting to source for file: {}", inputMetadata.getInputId());
            service.connect();
            InputStream fileStream = service.retrieveData(inputMetadata.getInputId());
            RoomConversation conversation = service.convertData(fileStream, inputMetadata.getInputFormat());
            saveDataToDb(conversation);
            setFileStatus(inputMetadata.getInputId(), FileProcessStatus.COMPLETED);
            //sendToExrichment();
        } else {
            log.debug("Input ID: {} already processed, skipping.. ", inputMetadata.getInputId());
            return;
        }
        
    }

    private void saveDataToDb(RoomConversation conversation){
        UUID id = UUID.randomUUID();
        conversationsDB.put(id, conversation);
    }

    private void setFileStatus(String inputId, FileProcessStatus status){
        dataFilesDaoImp.setStatus(inputId, status);
    }

    private boolean validateNotProcessed(String inputId) {
        FileProcessStatus status = dataFilesDaoImp.getFileRetStatus(inputId);
        if(status != null && (status.equals(FileProcessStatus.COMPLETED) || status.equals(FileProcessStatus.INITIATED))){
            return true;
        } else {
            //Could be we got an error for this file processed from another source and we want to handle it now, or its no started.
            return false;
        }
    }

}
