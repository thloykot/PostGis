package com.example.geometry.lineDB.converter;

import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;

public interface LineConverter {

    Line toLine(LineEntity lineEntity);
}
