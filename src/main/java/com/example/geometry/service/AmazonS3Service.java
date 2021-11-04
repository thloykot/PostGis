package com.example.geometry.service;

import java.io.File;
import java.io.IOException;

public interface AmazonS3Service {

    void uploadFile(String bucketName, File file);

    String downloadFile(String bucketName, String fileName) throws IOException;
}
