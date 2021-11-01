package com.example.geometry.dao.impl;

import com.example.geometry.dao.BackupDao;
import com.example.geometry.lineDB.fields.LineFields;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
@RequiredArgsConstructor
public class BackupDaoImpl implements BackupDao {

    private final DSLContext dslContext;

    @Override
    public String getLines() {
        return dslContext.select(LINE.ID,LINE.LENGTH, LineFields.GEOMETRY_SELECT_FIELD.apply("geometry"))
                .from(LINE).fetch().formatCSV();
    }
}
