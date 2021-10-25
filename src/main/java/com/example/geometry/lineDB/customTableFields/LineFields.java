package com.example.geometry.lineDB.customTableFields;

import com.example.geometry.lineDB.converter.GeoJsonToList;
import com.example.geometry.model.Line;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {
    public static final GeoJsonToList CONVERTER = new GeoJsonToList();

    public static Field<Object> objectField(Line line) {
        return DSL.field("ST_GeomFromGeoJSON('" +
                CONVERTER.to(line.getGeometry()) + "')");
    }

    public static Field<Integer> integerField(Line line) {
        return DSL.field("(ST_Length(ST_GeomFromGeoJSON('" +
                CONVERTER.to(line.getGeometry()) + "')))", SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
