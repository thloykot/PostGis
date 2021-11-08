package com.example.geometry;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeometryApplication {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public EnvironmentVariableCredentialsProvider awsCredentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3Client.builder().withRegion("us-east-1")
                .withCredentials(awsCredentialsProvider()).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GeometryApplication.class, args);
    }
}
