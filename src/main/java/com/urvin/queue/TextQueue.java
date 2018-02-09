package com.urvin.queue;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
//@DependsOn(value="sqsClient")
public class TextQueue {
    private String queueName;

    private AmazonSQSAsync amazonSQSAsync;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Autowired
    public TextQueue(){
        this.queueName = "TextMessageQueue.fifo";
        this.amazonSQSAsync = new AmazonSQSAsyncClient(new ProfileCredentialsProvider());
        this.amazonSQSAsync.setRegion(Region.getRegion(Regions.US_EAST_1));
        this.createQueue();
    }

    public void createQueue() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("FifoQueue", "true");
        attributes.put("ContentBasedDeduplication", "true");
        System.out.println("Queue name is :"+queueName);
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName).withAttributes(attributes);
        amazonSQSAsync.createQueue( createQueueRequest );
    }

    public void send(String message) {
        if(!StringUtils.isEmpty(message)) {
            System.out.println("message :"+message);
            SendMessageRequest sendMessageRequest = new SendMessageRequest(this.queueName,message);
            sendMessageRequest.setMessageGroupId("GroupId1");
            sendMessageRequest.setMessageDeduplicationId("Deduplication123");
            amazonSQSAsync.sendMessage(sendMessageRequest);
        }
    }

    @SqsListener("TextMessageQueue.fifo")
    public void receive(String message) {
        if(!StringUtils.isEmpty(message)) {
            System.out.println("Received Message :"+message);
        }
    }

}
