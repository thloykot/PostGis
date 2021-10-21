package com.example.geometry.lineDB.customTableFields;

import com.example.geometry.model.Line;
import org.jetbrains.annotations.NotNull;
import org.jooq.Context;
import org.jooq.Field;
import org.jooq.impl.CustomField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.generatedDB.tables.Line.LINE;

public class LineFields {

    public static Field<Object> getGeometryInsertField(Line line) {
        return new CustomField<>(line.getGeometry(), LINE.GEOMETRY.getDataType()) {
            @Override
            public void accept(@NotNull Context<?> context) {
                String l = "st_geomfromtext('" + line.getGeometry() + "', 4326)";
                context.sql(l);
            }
        };
    }

    public static Field<Integer> getLengthInsertField(Line line) {
        return new CustomField<>(String.valueOf(line.getLength()), SQLDataType.INTEGER) {
            @Override
            public void accept(Context<?> context) {
                String o = "(ST_Length(st_geomfromtext('" + line.getGeometry() + "', 4326)))";
                context.sql(o);
            }

            ;
        };
    }

    public static Field<String> getGeometrySelectField(){
        return DSL.field("ST_AsGeoJSON(geometry)", String.class, LINE.GEOMETRY);
    }
}
