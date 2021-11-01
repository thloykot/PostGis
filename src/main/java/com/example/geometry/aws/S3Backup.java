package com.example.geometry.aws;

import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Backup {

    private final BackupService backupService;

    private final Region region = Region.US_EAST_1;

    private final S3Client client = S3Client.builder().region(region).build();


    public String s() throws IOException {
        File file = new File(backupService.makeCSVFile());
        PutObjectRequest request = PutObjectRequest.builder().bucket("geometrybucket").build();
        return client.putObject(request, file.toPath()).requestChargedAsString();
    }
}
