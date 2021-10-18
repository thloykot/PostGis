package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.DataBaseHandler;
import com.example.geometry.model.Line;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext = DataBaseHandler.DSL_CONTEXT;

    @Override
    public int save(Line line) {
        return Objects.requireNonNull(dslContext.insertInto(LINE, LINE.LENGTH, LINE.GEOMETRY)
                .values(line.getLength(), line.getGeometry()).returningResult(LINE.ID).fetchOne()).into(int.class);
    }

    @Override
    public Optional<Line> findById(int id) {
        return dslContext.select(LINE.LENGTH, LINE.GEOMETRY).from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(Line.class);
    }
}
