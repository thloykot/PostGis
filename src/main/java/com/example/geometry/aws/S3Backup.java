package com.example.geometry.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Backup {

    private static final String BUCKET_NAME = "geometrybucket";

    private static final String KEY = System.getenv("KEY");

    private static final String SECRET_KEY = System.getenv("S_KEY");

    private static final BasicAWSCredentials CREDENTIALS = new BasicAWSCredentials(KEY, SECRET_KEY);

    private static final AmazonS3 S_3 = AmazonS3Client.builder().withRegion("us-east-1")
            .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS)).build();


    public static void makeBackup(String filePath) {
        File file = new File(filePath);
        S_3.putObject(BUCKET_NAME, KEY, file);
    }

    public static Optional<String> restore() {
        S3Object object = S_3.getObject(BUCKET_NAME, KEY);
        S3ObjectInputStream inputStream = object.getObjectContent();
        try {
            return Optional.of(new String(IOUtils.toByteArray(inputStream)));
        } catch (IOException e) {
            log.error("restoring error", e);
            return Optional.empty();
        }
    }
}
