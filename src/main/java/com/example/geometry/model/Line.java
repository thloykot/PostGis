package com.example.geometry.model;

import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Line{

    private final int length;

    private final List<Point> coordinates;

}
