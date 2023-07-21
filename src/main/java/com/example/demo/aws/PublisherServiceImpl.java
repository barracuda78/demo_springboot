package com.example.demo.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final AmazonSQS amazonSQS;

    @Value("${aws.sns.topicArn}")
    private String topicArn;

    @Value("${aws.sqs.queueUrl}")
    private String adminQueueUrl;

    @Override
    public void publishEvent(String event) {
        final SendMessageRequest msgRequest = new SendMessageRequest()
                .withQueueUrl(adminQueueUrl)
                .withMessageGroupId("first_group")
                .withMessageBody(event);
        amazonSQS.sendMessage(msgRequest);
    }

}
