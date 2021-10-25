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
        String l = "ST_GeomFromGeoJSON('" + CONVERTER.to(line.getGeometry()) + "')";
        return DSL.field(l);
    }

    public static Field<Integer> integerField(Line line) {
        String o = "(ST_Length(ST_GeomFromGeoJSON('" + CONVERTER.to(line.getGeometry()) + "')))";
        return DSL.field(o, SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
