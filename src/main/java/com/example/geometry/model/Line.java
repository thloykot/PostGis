package com.example.geometry.model;

import com.example.geometry.model.customObject.Point;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

import java.util.List;

@Value
@JsonPropertyOrder({"length","coordinates"})
public class Line {

    int length;

    List<Point> coordinates;
}