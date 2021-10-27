package com.example.geometry.service;

import com.example.geometry.model.Line;

import java.util.Optional;

public interface LineService {

    int save(Line line);

    Optional<Line> find(int id);
}
