package com.example.geometry.model;

import com.example.geometry.model.customObject.Point;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Line {

    private final int length;

    private final List<Point> coordinates;
}