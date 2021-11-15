package com.example.geometry.controller;

import com.example.geometry.aws.S3Backup;
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
    public ResponseEntity<String> backup() {
            return s3Backup.makeBackup().map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/restore")
    public ResponseEntity<String> restore() {
        return s3Backup.restore().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}
