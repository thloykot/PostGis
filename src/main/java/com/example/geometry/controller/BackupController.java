package com.example.geometry.controller;

import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("data")
public class BackupController {

    private final BackupService service;

    @GetMapping("/backup")
    public ResponseEntity<String> backup() {
        return service.makeCSVFile().map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/restore")
    public ResponseEntity<String> restore() {
        int restoredData = service.restore();
        return restoredData != 0 ?
                ResponseEntity.ok("Restoring successful. " + restoredData + " elements has been restored") :
                ResponseEntity.internalServerError().build();
    }

}
