package com.example.geometry.lineDB.fields;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LineFields {

    public static final Function<String, Field<Object>> GEOMETRY_FIELD = geometry ->
            DSL.field("ST_GeomFromText('LINESTRING(" + geometry + ")', 4326)");

    public static final Function<String, Field<Object>> GEOMETRY_FIELD_NO_GEO_TYPE = geometry ->
            DSL.field("ST_GeomFromText('" + geometry + "', 4326)");

    public static final Function<String, Field<Integer>> LENGTH_FIELD = geometry ->
            DSL.field("ST_length(ST_GeomFromText('LINESTRING(" + geometry + ")', 4326))", SQLDataType.INTEGER);

    public static final Function<String, Field<String>> GEOMETRY_SELECT_FIELD_AS_GEO_JSON = geometry ->
            DSL.field("ST_AsGeoJSON(" + geometry + ")", String.class);

    public static final Function<String, Field<String>> GEOMETRY_SELECT_FIELD_AS_TEXT = geometry ->
            DSL.field("ST_AsText(" + geometry + ")", String.class);
}
