package com.example.geometry.dao;


import com.example.geometry.model.LineEntity;

import java.util.Optional;


public interface LineDao {

    int save(String coordinates);

    Optional<LineEntity> findById(int id);

}
