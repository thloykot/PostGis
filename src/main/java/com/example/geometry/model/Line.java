package com.example.geometry.model;

import com.example.geometry.model.customObject.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Line {

    private final int length;

    private final List<Point> coordinates;
}