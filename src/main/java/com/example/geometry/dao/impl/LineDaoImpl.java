package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.fields.LineFields;
import com.example.geometry.model.LineEntity;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generatedDB.tables.records.LineRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.geometry.lineDB.fields.LineFields.*;
import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
@RequiredArgsConstructor
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext;

    @Override
    public int save(String coordinates) {
        return Objects.requireNonNull(dslContext.insertInto(LINE, LINE.GEOMETRY, LINE.LENGTH)
                .values(LineFields.GEOMETRY_FIELD.apply(coordinates), LineFields.LENGTH_FIELD.apply(coordinates))
                .returningResult(LINE.ID).fetchOne()).into(int.class);
    }

    @Override
    public Optional<LineEntity> findById(int id) {
        return dslContext.select(LINE.ID, LINE.LENGTH, GEOMETRY_SELECT_FIELD_AS_GEO_JSON.apply("geometry"))
                .from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(LineEntity.class);
    }

    @Override
    public String getLinesCsv() {
        return dslContext.select(LINE.ID, LINE.LENGTH, GEOMETRY_SELECT_FIELD_AS_TEXT.apply("geometry"))
                .from(LINE).fetch().formatCSV();
    }

    @Override
    public int importBackupData(List<LineEntity> lineEntities) {
        return dslContext.batchInsert(makeRecordList(lineEntities)).execute().length;
    }

    @Override
    public void truncateTable() {
        dslContext.truncateTable(LINE).execute();
    }

    private List<LineRecord> makeRecordList(List<LineEntity> lineEntities) {
        return lineEntities.stream()
                .map(this::makeRecord)
                .collect(Collectors.toUnmodifiableList());
    }

    private LineRecord makeRecord(LineEntity lineEntity) {
        LineRecord lineRecord = new LineRecord();
        lineRecord.setId(lineEntity.getId());
        lineRecord.setLength(lineEntity.getLength());
        lineRecord.setGeometry(GEOMETRY_FIELD_NO_GEO_TYPE.apply(lineEntity.getCoordinates()));
        return lineRecord;
    }
}
