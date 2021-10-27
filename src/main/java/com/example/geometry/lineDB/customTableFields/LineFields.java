package com.example.geometry.lineDB.customTableFields;


import com.example.geometry.model.Line;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {

    public static Field<Object> coordinatesField(String geometry) {
        return DSL.field("ST_GeomFromText('LINESTRING(" + geometry + ")',4326)");
    }

    public static Field<Integer> lengthField(String geometry) {
        return DSL.field("ST_length(ST_GeomFromText('LINESTRING(" + geometry + ")',4326))", SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
