package com.example.geometry;

import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GeometryApplication {
    public static void main(String[] args) throws JsonProcessingException {
        Point d = new Point(2.5,5.6);
        SpringApplication.run(GeometryApplication.class, args);
        System.out.println(new ObjectMapper().writeValueAsString(new Line(12, List.of(new Point(7.4,5),d))));
    }
}
