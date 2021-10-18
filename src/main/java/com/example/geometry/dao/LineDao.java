package com.example.geometry.dao;

import com.example.geometry.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LineDao{

    int save(Line line);

    Optional<Line> findById(int id);
}
