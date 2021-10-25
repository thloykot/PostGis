package com.example.geometry.dao;

import com.example.geometry.model.LinePoJo;

import java.util.Optional;


public interface LineDao{

    int save(LinePoJo linePoJo);

    Optional<LinePoJo> findById(int id);
}
