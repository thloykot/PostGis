package com.example.geometry.dao.impl;

import com.example.geometry.dao.BackupDao;
import com.example.geometry.dto.LineDto;
import com.example.geometry.model.customObject.Point;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.geometry.lineDB.fields.LineFields.GEOMETRY_FIELD;
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
    public int restore(List<LineDto> lineDtos) {
        dslContext.truncate(LINE).cascade().execute();
        return saveBackupData(lineDtos);
    }

    private int saveBackupData(List<LineDto> lineDtos) {
        return lineDtos.stream()
                .mapToInt(this::insertData)
                .sum();
    }

    private int insertData(LineDto lineDto) {
        return dslContext.insertInto(LINE, LINE.ID, LINE.LENGTH, LINE.GEOMETRY)
                .values(lineDto.getId(), lineDto.getLength(), GEOMETRY_FIELD
                        .apply(getCoordinateString(lineDto.getCoordinates()))).execute();

    }

    private String getCoordinateString(List<Point> points) {
        return points.stream()
                .map(Point::toString).collect(Collectors.joining(", "));
    }

}
