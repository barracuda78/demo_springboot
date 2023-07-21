package com.example.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
//    @Profile("local")
    AmazonSQS localAmazonSQSClient(
            @Autowired @Qualifier("localAwsCredentialsProvider") AWSCredentialsProvider credentialProvider
    ) {
        final String fakeHost = "http://localhost:4566";
        final String region = Regions.US_EAST_1.getName();
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(fakeHost, region))
                .withCredentials(credentialProvider)
                .build();
    }

    @Bean
//    @Profile("local")
    AWSCredentialsProvider localAwsCredentialsProvider() {
        return new AWSCredentialsProvider() {

            @Override
            public AWSCredentials getCredentials() {
                return new BasicAWSCredentials("foo", "bar");
            }

            @Override
            public void refresh() {
                //nothing to override here
            }

        };
    }

}
