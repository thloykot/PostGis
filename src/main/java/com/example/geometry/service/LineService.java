package com.example.geometry.service;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;

import java.util.Optional;

public interface LineService {

    int save(LineEntity line);

    Optional<Line> find(int id);
}
