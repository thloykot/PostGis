package com.example.geometry.lineDB.fields;

import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.util.function.Function;

public class LineFields {

    public static final Function<String, Field<Object>> GEOMETRY_FIELD = geometry ->
            DSL.field("ST_GeomFromText('LINESTRING(" + geometry + ")', 4326)");

    public static final Function<String, Field<Integer>> LENGTH_FIELD = geometry ->
            DSL.field("ST_length(ST_GeomFromText('LINESTRING(" + geometry + ")', 4326))", SQLDataType.INTEGER);

    public static final Function<String, Field<String>> GEOMETRY_SELECT_FIELD = geometry ->
            DSL.field("ST_AsGeoJSON(" + geometry +")", String.class);
}
