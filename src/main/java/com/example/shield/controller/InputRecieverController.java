package org.springframework.samples.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.shield.consts.RestConsts;
import org.springframework.samples.shield.model.InputMetadata;
import org.springframework.samples.shield.service.dataRetrieval.DataRetrievalManager;
import org.springframework.samples.shield.service.dataRetrieval.DataRetrievalServiceFactory;
import org.springframework.samples.shield.service.dataRetrieval.IDataRetrievalService;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class InputRecieverController {

    @Autowired
    DataRetrievalManager dataRetrievalManager;

    @RequestMapping(value=RestConsts.Input.INPUT, method=RequestMethod.POST)
    public void recieveInputMetadata( @RequestHeader(value = RestConsts.CommonHeaders.tenantId) String tenantId,
                                        @RequestBody InputMetadata inputMetadata) {

        //TODO get dto and convert into internal object
        //TODO validations
        //I would make this async and use kafka to send the messages to another service - wont do it due to time limitation, keeping it simple
        dataRetrievalManager.processData(inputMetadata);
        
    }
    

}
