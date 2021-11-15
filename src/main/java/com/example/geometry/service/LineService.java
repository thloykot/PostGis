package com.example.geometry.service;

import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LineService {

    int save(List<Point> points);

    Optional<Line> find(int id) throws JsonProcessingException;

    void backupLineData() throws IOException;

    int restoreDataFromS3() throws IOException;
}