package com.example.geometry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LineJson {
    private final int length;

    private final List<Double> geometry;
}