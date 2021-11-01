package com.example.geometry;

import com.example.geometry.dao.LineDao;
import com.example.geometry.lineDB.converter.LineConverter;
import com.example.geometry.lineDB.converter.impl.LineConverterImpl;
import com.example.geometry.model.Line;
import com.example.geometry.model.LineEntity;
import com.example.geometry.model.customObject.Point;
import com.example.geometry.service.impl.LineServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GeometryApplication.class})
@RunWith(MockitoJUnitRunner.class)
class GeometryApplicationTests {

    @InjectMocks
    private LineServiceImpl lineService;

    @Mock
    private LineDao lineDao;

    @Mock
    private LineConverter lineConverter;



    private static final List<Point> POINT_LIST = List.of(new Point(3.4, 54.5), new Point(87.4, 392.5));
    private static final int ID = 1;
    private static final String COORDINATES = "3.4 54.5, 87.4 392.5";


    @Test
    void testSave() {

        when(lineService.save(POINT_LIST)).thenReturn(ID);
        when(lineDao.save(COORDINATES)).thenReturn(ID);

        assertThat(lineDao.save(COORDINATES), is(ID));
        assertThat(lineService.save(POINT_LIST), is(ID));
    }

    @Test
    void testFind() {
        Line line = new Line(11, POINT_LIST);
        LineEntity lineEntity = new LineEntity(line.getLength(), COORDINATES);

        when(lineDao.findById(ID)).thenReturn(Optional.of(lineEntity));
        when(lineConverter.toLine(lineEntity)).thenReturn(line);

        assertThat(lineService.find(ID), is(Optional.of(line)));
    }

}
