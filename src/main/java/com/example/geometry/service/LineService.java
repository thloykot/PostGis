package com.example.geometry.service;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineJson;

import java.util.Optional;

public interface LineService {

    int save(Line line);

    Optional<LineJson> find(int id);
}
