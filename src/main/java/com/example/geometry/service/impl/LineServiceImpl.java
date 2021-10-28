package com.example.geometry.service.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.converter.impl.LineConverterImpl;
import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;
import com.example.geometry.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService {

    private final LineDao lineDao;

    private final LineConverterImpl lineConverter;

    @Override
    public int save(List<Point> points) {
        log.info("Saving line");
        return lineDao.save(points.stream()
                .map(Point::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public Optional<Line> find(int id) {
        log.info("Finding line by id:{}", id);
        return lineDao.findById(id).map(lineConverter::toLine);
    }
}
