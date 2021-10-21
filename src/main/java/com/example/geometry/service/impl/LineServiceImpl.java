package com.example.geometry.service.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineJson;
import com.example.geometry.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final LineDao lineDao;

    @Override
    public int save(Line line) {
        return lineDao.save(line);
    }

    @Override
    public Optional<LineJson> find(int id) {
        return lineDao.findById(id).map(Line::toLineJson);
    }
}
