package com.example.geometry.dto;

import com.example.geometry.model.customObject.Point;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class LineDto {

    private final int id;

    private final int length;

    private final List<Point> coordinates;
}
