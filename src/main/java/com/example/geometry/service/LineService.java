package com.example.geometry.service;

import com.example.geometry.model.Line;
import com.example.geometry.model.LinePoJo;

import java.util.Optional;

public interface LineService {

    int save(LinePoJo linePoJo);

    Optional<Line> find(int id);
}
