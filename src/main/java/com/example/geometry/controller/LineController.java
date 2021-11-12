package com.example.geometry.controller;

import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;
import com.example.geometry.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/line")
@RestController
@Slf4j
public class LineController {

    private final LineService lineService;

    @GetMapping("/hello")
    public ResponseEntity<String> ss(){
        return ResponseEntity.ok("hello");
    }

    @PutMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody List<Point> points) {
        return ResponseEntity.ok(lineService.save(points));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Line> findById(@PathVariable("id") int id) throws JsonProcessingException {
        return lineService.find(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/backup")
    public ResponseEntity<String> backup() {
        try {
            lineService.backupLineData();
            return ResponseEntity.ok("Backup successful");
        } catch (IOException e) {
            log.error("Error during backup", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/restore")
    public ResponseEntity<String> restore() {
        try {
            return ResponseEntity.ok("Restoring successful. " + lineService.restoreDataFromS3()
                    + " elements has been restored");
        } catch (IOException e) {
            log.error("Error during restore", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
