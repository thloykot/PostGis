package com.example.geometry.service.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.converter.CoordinateConverter;
import com.example.geometry.model.Line;
import com.example.geometry.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService {

    private final LineDao lineDao;
    private final CoordinateConverter coordinateConverter;

    @Override

    public int save(Line line) {
        log.info("Saving line");
        return lineDao.save(line);
    }

    @Override
    public Optional<Line> find(int id) {
        log.info("Finding line by id:{}", id);
        return lineDao.findById(id).map(coordinateConverter::from);
    }
}
