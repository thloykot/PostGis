package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.DataBaseHandler;
import com.example.geometry.lineDB.customTableFields.LineFields;
import com.example.geometry.model.Line;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext = DataBaseHandler.DSL_CONTEXT;

    @Override
    public Integer save(Line line) {
        return dslContext.insertInto(LINE, LINE.GEOMETRY, LINE.LENGTH)
                .values(LineFields.getGeometryInsertField(line), LineFields.getLengthInsertField(line))
                .returningResult(LINE.ID).fetchOne().into(int.class);
    }

    @Override
    public Optional<Line> findById(int id) {
        return dslContext.select(LINE.LENGTH, LineFields.getGeometrySelectField())
                .from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(Line.class);
    }

}
