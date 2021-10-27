package com.example.geometry.lineDB.customTableFields;


import com.example.geometry.model.Line;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {

    public static Field<Object> coordinatesField(Line lineEntity) {
        String clearedCoordinates = lineEntity.getCoordinates().toString().replace("[","").replace("]","");
        return DSL.field("ST_GeomFromText('LINESTRING(" + clearedCoordinates + ")',4326)");
    }

    public static Field<Integer> lengthField(Field<Object> coordinates) {
        return DSL.field("ST_length(" + coordinates + ")", SQLDataType.INTEGER);
    }

    public static Field<String> getGeometrySelectField() {
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
