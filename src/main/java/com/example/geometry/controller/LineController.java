package com.example.geometry.controller;

import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;
import com.example.geometry.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.core.annotation.Incubating;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/line")
@RestController
@Slf4j
@EnableAspectJAutoProxy
public class LineController {

    private final LineService lineService;

    @Timed(value = "line.save.timed", description = "Time taken to save line")
    @PutMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody List<Point> points) {
        return ResponseEntity.ok(lineService.save(points));
    }
    @Timed(value = "line.find.counted", description = "Timed findLine")
    @GetMapping("/find/{id}")
    public ResponseEntity<Line> findById(@PathVariable("id") int id) throws JsonProcessingException {
        return lineService.find(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Incubating(since = "ihor")
    @GetMapping("/test")
    public ResponseEntity<String> stringResponseEntity(){
        return ResponseEntity.ok("Hello");
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
