package com.example.geometry.controller;

import com.example.geometry.aws.S3Backup;
import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("data")
public class BackupController {

    private final S3Backup s3Backup;

    @GetMapping("/backup")
    public ResponseEntity<String> backup(){
        try {
            return ResponseEntity.ok(s3Backup.s());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

}
