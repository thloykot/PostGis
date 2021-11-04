package com.example.geometry.lineDB.converter;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LineCoordinatesConverter {

    Line toLine(LineEntity lineEntity) throws JsonProcessingException;
}
