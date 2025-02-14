package org.springframework.samples.shield.service.dataRetrieval;

import org.springframework.samples.shield.model.InputMetadata;
import org.springframework.stereotype.Service;

@Service
public class S3DataService implements IDataRetrievalService {

    @Override
    public String processData(InputMetadata metadata) {
        //TODO implementation for 
        return "Retrieving data from S3 for " + metadata.getInputId();
    }

    @Override
    public boolean validateNotProcessed(String inputId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateNotProcessed'");
    }

    @Override
    public void connect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }

    @Override
    public void copyData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyData'");
    }
}
