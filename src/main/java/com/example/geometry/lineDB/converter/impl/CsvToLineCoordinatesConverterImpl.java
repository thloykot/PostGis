package com.example.geometry.lineDB.converter.impl;

import com.example.geometry.lineDB.converter.CsvToLineCoordinatesConverter;
import com.example.geometry.model.LineEntity;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
public class CsvToLineCoordinatesConverterImpl implements CsvToLineCoordinatesConverter {

    @Override
    public List<LineEntity> convert(String csv) throws IOException {
        try (CSVReader csvReader = new CSVReaderBuilder(new StringReader(csv)).withSkipLines(1).build()) {
            return makeLineDtoList(csvReader.readAll());
        }
    }

    private List<LineEntity> makeLineDtoList(List<String[]> stringsList) {
        return stringsList.stream()
                .filter(strings -> strings.length == 3)
                .map(strings -> makeLineEntity(strings[0], strings[1], strings[2]))
                .collect(Collectors.toUnmodifiableList());
    }

    private LineEntity makeLineEntity(String id, String length, String coordinates) {
        return new LineEntity(Integer.parseInt(id), Integer.parseInt(length), coordinates);
    }
}
