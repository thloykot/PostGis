package com.example.geometry.lineDB.converter;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.LineCoordinates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoordinateConverter implements Converter<LineEntity, Line> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Line from(LineEntity lineEntity) {
        try {
            return new Line(lineEntity.getLength(), objectMapper
                    .readValue(lineEntity.getCoordinates(), LineCoordinates.class).getCoordinates());
        } catch (JsonProcessingException e) {
            log.info("Got exception {}",e);
        }
        return null;
    }

    @Override
    public LineEntity to(Line line) {
        try {
            return new LineEntity(line.getLength(), objectMapper
                    .writeValueAsString(line.getCoordinates()));
        } catch (JsonProcessingException e) {
            log.info("Got exception {}",e);
        }
        return null;
    }

    @Override
    public Class<LineEntity> fromType() {
        return LineEntity.class;
    }

    @Override
    public Class<Line> toType() {
        return Line.class;
    }
}
