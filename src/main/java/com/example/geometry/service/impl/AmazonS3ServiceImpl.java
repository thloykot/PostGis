package com.example.geometry.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.geometry.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final AmazonS3 amazonS3;

    @Override
    public void uploadFile(String bucketName, File file) {
        amazonS3.putObject(bucketName, file.getName(), file);
    }

    @Override
    public String downloadFile(String bucketName, String fileName) throws IOException {
        try (S3ObjectInputStream stream = amazonS3.getObject(bucketName, fileName).getObjectContent()) {
            return IOUtils.toString(stream);
        }
    }
}
