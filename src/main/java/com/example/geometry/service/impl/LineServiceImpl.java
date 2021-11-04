package com.example.geometry.service.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.converter.CsvToLineCoordinatesConverter;
import com.example.geometry.lineDB.converter.LineCoordinatesConverter;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.Point;
import com.example.geometry.service.AmazonS3Service;
import com.example.geometry.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private static final String LINE_BUCKET_NAME = "geometrybucket";

    private static final String FILE_NAME = "db_backup.csv";

    private static final String PATH = "src/main/resources/backup/" + FILE_NAME;

    private final LineDao lineDao;

    private final LineCoordinatesConverter lineCoordinatesConverter;

    private final CsvToLineCoordinatesConverter csvToLineCoordinatesConverter;

    private final AmazonS3Service amazonS3Service;


    @Override
    public int save(List<Point> points) {
        return lineDao.save(getCoordinateString(points));
    }

    @Override
    public Optional<Line> find(int id) throws JsonProcessingException {
        Optional<LineEntity> byId = lineDao.findById(id);
        if (byId.isPresent()) {
            return Optional.of(lineCoordinatesConverter.toLine(byId.get()));
        }
        return Optional.empty();
    }

    @Override
    public void backupLineData() throws IOException {
        String lineData = lineDao.getLinesCsv();
        File backupFile = new File(PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
            writer.write(lineData);
            amazonS3Service.uploadFile(LINE_BUCKET_NAME, backupFile);

        } finally {
            backupFile.delete();
        }
    }

    @Override
    @Transactional
    public int restoreDataFromS3() throws IOException {
        List<LineEntity> lineEntities = csvToLineCoordinatesConverter
                .convert(amazonS3Service.downloadFile(LINE_BUCKET_NAME, FILE_NAME));
        lineDao.truncateTable();
        return lineDao.importBackupData(lineEntities);
    }

    private String getCoordinateString(List<Point> points) {
        return points.stream()
                .map(Point::toString).collect(Collectors.joining(", "));
    }
}
