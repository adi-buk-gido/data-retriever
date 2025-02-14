package org.springframework.samples.shield.service.dataRetrieval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.shield.model.InputMetadata;

@Component
public class DataRetrievalManager {

    @Autowired
    DataRetrievalServiceFactory dataRetrievalServiceFactory;

    @Autowired
    EnrichmentService EnrichmentService;


    public void processData(InputMetadata inputMetadata){
        IDataRetrievalService service = DataRetrievalServiceFactory.getService(inputMetadata.getSourceType());
        service.validateNotProcessed(inputMetadata.getInputId());
        service.connect();
        service.processData(inputMetadata);
        service.disconnect();
        saveDataToDb();
        EnrichmentService.enrich();


        
    }

}
