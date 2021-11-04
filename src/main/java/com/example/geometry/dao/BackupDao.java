package com.example.geometry.dao;

import com.example.geometry.dto.LineDto;

import java.util.List;

public interface BackupDao {

    String getLines();

    int restore(List<LineDto> lineDtos);
}
