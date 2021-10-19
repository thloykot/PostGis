package com.example.geometry.dao;

import com.example.geometry.model.Line;

import java.util.Optional;


public interface LineDao{

    Integer save(Line line);

    Optional<Line> findById(int id);
}
