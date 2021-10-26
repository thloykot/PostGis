package com.example.geometry.lineDB.customTableFields;

import com.example.geometry.model.Line;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {

    public static Field<Object> coordinatesField(Line line) {
        return DSL.field("ST_GeomFromGeoJSON('{\"type\":\"LineString\",\"coordinates\":" + line.getCoordinates() + "}')");
    }

    public static Field<Integer> lengthField(Field<Object> coordinates) {
        return DSL.field("ST_length(" + coordinates + ")", SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}