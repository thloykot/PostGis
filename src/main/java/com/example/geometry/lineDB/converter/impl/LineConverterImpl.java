package com.example.geometry.lineDB.converter.impl;

import com.example.geometry.lineDB.converter.LineConverter;
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
public class LineConverterImpl implements LineConverter {

    private final ObjectMapper objectMapper;

    public Line toLine(LineEntity lineEntity) {
        try {
            return new Line(lineEntity.getLength(),
                    streamLine(objectMapper.readValue(lineEntity.getCoordinates(), LineCoordinates.class).getCoordinates()));
        } catch (JsonProcessingException e) {
            log.error("Mapping error!", e);
            return null;
        }
    }

    private List<Point> streamLine(List<List<Double>> coordinates) {
        return coordinates.stream().filter(doubles -> doubles.size() == 2)
                .map(doubles -> new Point(doubles.get(0), doubles.get(1))).collect(Collectors.toUnmodifiableList());
    }

}
