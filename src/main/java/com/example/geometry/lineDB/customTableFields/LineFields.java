package com.example.geometry.lineDB.customTableFields;

import com.example.geometry.model.LinePoJo;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {

    public static Field<Object> objectField(LinePoJo linePoJo) {
        return DSL.field("ST_GeomFromGeoJSON('" + linePoJo.getGeometry() + "')");
    }

    public static Field<Integer> integerField(LinePoJo linePoJo) {
        return DSL.field("(ST_Length(ST_GeomFromGeoJSON('" +
                linePoJo.getGeometry() + "')))", SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
