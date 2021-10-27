package com.example.geometry.dao.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.customTableFields.LineFields;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static org.jooq.generatedDB.tables.Line.LINE;

@Repository
@AllArgsConstructor
public class LineDaoImpl implements LineDao {

    private final DSLContext dslContext;

    @Override
    public int save(Line line) {
        String coordinates = line.getCoordinates().toString().replace("[","").replace("]","");
        return Objects.requireNonNull(dslContext.insertInto(LINE, LINE.GEOMETRY, LINE.LENGTH)
                .values(LineFields.coordinatesField(coordinates), LineFields.lengthField(coordinates))
                .returningResult(LINE.ID).fetchOne()).into(int.class);
    }

    @Override
    public Optional<LineEntity> findById(int id) {
        return dslContext.select(LINE.LENGTH, LineFields.getGeometrySelectField())
                .from(LINE).where(LINE.ID.eq(id)).fetchOptionalInto(LineEntity.class);
    }
}
