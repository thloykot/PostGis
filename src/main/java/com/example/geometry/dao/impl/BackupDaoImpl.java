package com.example.geometry.dao.impl;

import com.example.geometry.dao.BackupDao;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import static com.example.geometry.lineDB.fields.LineFields.GEOMETRY_SELECT_FIELD;
import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
@RequiredArgsConstructor
public class BackupDaoImpl implements BackupDao {

    private final DSLContext dslContext;

    @Override
    public String getLines() {
        return dslContext.select(LINE.ID, LINE.LENGTH, GEOMETRY_SELECT_FIELD.apply("geometry"))
                .from(LINE).fetch().formatCSV();
    }

    @Override
    public int restore(String data) throws IOException {
        dslContext.truncate(LINE).cascade();
        return dslContext.loadInto(LINE).loadCSV(data)
                .fields(LINE.ID, LINE.LENGTH, LINE.GEOMETRY).execute().processed();
    }
}
