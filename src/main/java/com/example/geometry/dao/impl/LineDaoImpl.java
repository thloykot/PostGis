package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.model.LineEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
@RequiredArgsConstructor
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext;

    private final Function<String, Field<Object>> geometryField = geometry ->
            DSL.field("ST_GeomFromText('LINESTRING(" + geometry + ")', 4326)");

    private final Function<String, Field<Integer>> lengthField = geometry ->
            DSL.field("ST_length(ST_GeomFromText('LINESTRING(" + geometry + ")', 4326))", SQLDataType.INTEGER);

    private final Function<String, Field<String>> geometrySelectField = geometry -> DSL.field("ST_AsGeoJSON(" + geometry +")", String.class);

    @Override
    public int save(String coordinates) {
        return Objects.requireNonNull(dslContext.insertInto(LINE, LINE.GEOMETRY, LINE.LENGTH)
                .values(geometryField.apply(coordinates), lengthField.apply(coordinates))
                .returningResult(LINE.ID).fetchOne()).into(int.class);
    }

    @Override
    public Optional<LineEntity> findById(int id) {
        return dslContext.select(LINE.LENGTH, geometrySelectField.apply("geometry"))
                .from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(LineEntity.class);
    }
}
