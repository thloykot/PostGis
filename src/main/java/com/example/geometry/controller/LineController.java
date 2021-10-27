package com.example.geometry.controller;

import com.example.geometry.model.Line;
import com.example.geometry.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/line")
@RestController
public class LineController {

    private final LineService lineService;

    @PutMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody Line line) {
        return ResponseEntity.ok(lineService.save(line));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Line> findById(@PathVariable("id") int id) {
        return lineService.find(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
