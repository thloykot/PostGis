package com.example.geometry.lineDB.converter.impl;

import com.example.geometry.lineDB.converter.LineCoordinatesConverter;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.LineCoordinates;
import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class LineCoordinatesConverterImpl implements LineCoordinatesConverter {

    private final ObjectMapper objectMapper;

    @Override
    public Line toLine(LineEntity lineEntity) throws JsonProcessingException {
        return new Line(lineEntity.getLength(),
                getPoints(objectMapper
                        .readValue(lineEntity.getCoordinates(), LineCoordinates.class)));
    }

    private List<Point> getPoints(LineCoordinates coordinates) {
        return coordinates.getCoordinates().stream()
                .filter(doubles -> doubles.size() == 2)
                .map(doubles -> new Point(doubles.get(0), doubles.get(1)))
                .collect(Collectors.toUnmodifiableList());
    }

}
