package org.springframework.samples.shield.service.dataRetrieval;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.shield.model.SourceType;
import org.springframework.stereotype.Component;

@Component
public class DataRetrievalServiceFactory {

    private static Map<SourceType, IDataRetrievalService> serviceMap;
    
        @Autowired
        private S3DataService s3DataService;
    
        @Autowired
        private SFTPDataService sftpDataService;
    
        @Autowired
        private LocalDataService localDataService;
    
    
        @PostConstruct
        public void init(){
            DataRetrievalServiceFactory.serviceMap = Map.of(
                    SourceType.S3, s3DataService,
                    SourceType.SFTP, sftpDataService,
                    SourceType.LOCAL, localDataService
            );
        }
    
        public static IDataRetrievalService getService(SourceType sourceType) {
            return serviceMap.get(sourceType);
    }

}
