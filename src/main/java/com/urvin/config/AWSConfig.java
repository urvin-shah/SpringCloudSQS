package com.urvin.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AWSConfig {

    /*@Bean(value = "sqsClient")
    public AmazonSQSAsync getSQSClient() {
        AmazonSQSAsync sqsClient = new AmazonSQSAsyncClient(new ProfileCredentialsProvider());
        sqsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        System.out.println("sqsClient get initialized :"+sqsClient.listQueues());
        return sqsClient;
    }*/
}
