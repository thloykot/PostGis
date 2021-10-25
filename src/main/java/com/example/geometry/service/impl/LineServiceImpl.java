package com.example.geometry.service.impl;

import com.example.geometry.dao.LineDao;
import com.example.geometry.model.Line;
import com.example.geometry.model.LinePoJo;
import com.example.geometry.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final LineDao lineDao;

    @Override
    public int save(LinePoJo linePoJo) {
        return lineDao.save(linePoJo);
    }

    @Override
    public Optional<Line> find(int id) {
        return lineDao.findById(id).map(Line::new);
    }
}
