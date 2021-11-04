package com.example.geometry.lineDB.converter.impl;

import com.example.geometry.lineDB.converter.LineConverter;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.LineCoordinates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LineConverterImpl implements LineConverter {

    private final ObjectMapper objectMapper;

    public Line toLine(LineEntity lineEntity) {
        try {
            return new Line(lineEntity.getLength(),
                    objectMapper.readValue(lineEntity.getCoordinates(), LineCoordinates.class).getPoints());
        } catch (JsonProcessingException e) {
            log.error("Mapping error!", e);
            return null;
        }
    }


}
