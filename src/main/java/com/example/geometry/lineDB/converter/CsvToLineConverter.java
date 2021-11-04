package com.example.geometry.lineDB.converter;

import com.example.geometry.dto.LineDto;

import java.util.List;

public interface CsvToLineConverter {

    List<LineDto> convert(String s);
}
