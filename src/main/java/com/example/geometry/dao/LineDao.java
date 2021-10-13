package com.example.geometry.dao;

import com.example.geometry.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineDao extends JpaRepository<Line,Integer> {
}
