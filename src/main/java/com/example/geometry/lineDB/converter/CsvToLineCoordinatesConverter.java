package com.example.geometry.lineDB.converter;

import com.example.geometry.model.LineEntity;

import java.io.IOException;
import java.util.List;

public interface CsvToLineCoordinatesConverter {

    List<LineEntity> convert(String s) throws IOException;
}
