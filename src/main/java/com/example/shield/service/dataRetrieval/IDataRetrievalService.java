package org.springframework.samples.shield.service.dataRetrieval;

import org.springframework.samples.shield.model.InputMetadata;

public interface IDataRetrievalService {

    /**
     * Validate the InputId was not processed
     * @param metadata
     * @return
     */
    boolean validateNotProcessed(String inputId);

    /**
     * Connect to the relevant source
     */
    void connect();

    /**
     * Disconnect from the relevant source
     */
    void disconnect();

    /**
     * Copy the data to our DB and to S3 bucket
     */
    void retrieveData();

    
    /**
     * Convert the data based on the source to an internal format
     */
    String convertData();

}
