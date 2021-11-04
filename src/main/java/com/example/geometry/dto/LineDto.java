package com.example.geometry.dto;

import com.example.geometry.model.customObject.Point;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
public class LineDto {

    int id;

    int length;

    List<Point> coordinates;
}
