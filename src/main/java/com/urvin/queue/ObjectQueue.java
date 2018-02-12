package com.urvin.queue;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.urvin.domain.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class ObjectQueue {

    private String queueName;

    private AmazonSQSAsync amazonSQSAsync;

    private QueueMessagingTemplate queueMessagingTemplate;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Autowired
    public ObjectQueue(){
        this.queueName = "ObjectMessageQueue.fifo";
        this.amazonSQSAsync = new AmazonSQSAsyncClient(new ProfileCredentialsProvider());
        this.amazonSQSAsync.setRegion(Region.getRegion(Regions.US_EAST_1));
        this.queueMessagingTemplate = new QueueMessagingTemplate(this.amazonSQSAsync);
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

    // Send message using the SendMessageRequest
    public void send(MailMessage message) {
        if(!StringUtils.isEmpty(message)) {
            System.out.println("message :"+message);

            SendMessageRequest sendMessageRequest = new SendMessageRequest(this.queueName, MessageBuilder.withPayload(message).build().toString());
            sendMessageRequest.setMessageGroupId("GroupId134");
            sendMessageRequest.setMessageDeduplicationId("Deduplication12312");
            amazonSQSAsync.sendMessage(sendMessageRequest);
        }
    }

    // Send message using the QueueMessagingTemplate.
    public void sendMessage(MailMessage message) {
        if(message != null) {
            System.out.println("Message is:"+message);
            Map<String,Object> messageHeaders = new HashMap<String,Object>();
            messageHeaders.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER,"GrpId13");
            this.queueMessagingTemplate.convertAndSend(this.queueName,message,messageHeaders);
        }
    }

    @SqsListener("ObjectMessageQueue.fifo")
    public void receive(MailMessage message) {
        if(message != null) {
            System.out.println("Received Message :");
            System.out.println("Userlist :"+message.getUserList());
            System.out.println("From: "+message.getFromField());
            System.out.println("To:"+message.getToField());
            System.out.println("Subject Field:"+message.getSubjectField());
            System.out.println("Message body:"+message.getMessageField());
            System.out.println("Optional Sender :"+message.getOptionalSenderName());
        }
    }
}
