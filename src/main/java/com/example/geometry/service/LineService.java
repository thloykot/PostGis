package com.example.geometry.service;

import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;

import java.util.List;
import java.util.Optional;

public interface LineService {

    int save(List<Point> points);

    Optional<Line> find(int id);
}