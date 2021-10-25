package com.example.geometry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class Line implements Serializable {

    private final int length;

    private final List<Double> geometry;
}
