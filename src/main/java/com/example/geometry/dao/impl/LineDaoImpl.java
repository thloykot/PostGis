package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.DataBaseHandler;
import com.example.geometry.model.Line;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext = DataBaseHandler.DSL_CONTEXT;

    @Override
    public Integer save(Line line) {
        return dslContext.query("insert into line (geometry, length) values" +
                "(st_geomfromtext('" + line.getGeometry() + "', 4326), ST_Length(st_geomfromtext('" + line.getGeometry() + "', 4326)))").execute();
    }

    @Override
    public Optional<Line> findById(int id) {
        return dslContext.select(LINE.LENGTH, DSL.field("ST_AsText(geometry)", String.class, LINE.GEOMETRY)).from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(Line.class);
    }
}
