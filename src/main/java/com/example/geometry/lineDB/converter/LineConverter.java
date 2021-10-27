package com.example.geometry.lineDB.converter;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.LineCoordinates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LineConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Line toLine(LineEntity lineEntity) {
        try {
            return new Line(lineEntity.getLength(), OBJECT_MAPPER.
                    readValue(lineEntity.getCoordinates(), LineCoordinates.class).getPoints());
        } catch (JsonProcessingException e) {
            log.error("Mapping error!");
        }
        return null;
    }

}
