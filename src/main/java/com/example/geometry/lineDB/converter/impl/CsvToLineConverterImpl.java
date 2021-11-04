package com.example.geometry.lineDB.converter.impl;

import com.example.geometry.dto.LineDto;
import com.example.geometry.lineDB.converter.CsvToLineConverter;
import com.example.geometry.model.customObject.LineCoordinates;
import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvToLineConverterImpl implements CsvToLineConverter {

    private final ObjectMapper objectMapper;

    public List<LineDto> convert(String csv) {
        String coordinates = csv.replace("id,length,ST_AsGeoJSON(geometry)\n", "");
        CSVReader csvReader = new CSVReader(new StringReader(coordinates));
        try {
            return csvReader.readAll().stream().filter(strings -> strings.length == 3)
                    .map(strings -> new LineDto(Integer.parseInt(strings[0]),
                            Integer.parseInt(strings[1]), makeCoordinates(strings[2]))).collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            log.error("Mapping error", e);
            return null;
        }
    }

    private List<Point> makeCoordinates(String coordinates) {
        try {
            return objectMapper.readValue(coordinates, LineCoordinates.class).getPoints();
        } catch (JsonProcessingException e) {
            log.error("ObjectMapper error", e);
            return null;
        }
    }
}
