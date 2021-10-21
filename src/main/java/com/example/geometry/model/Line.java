package com.example.geometry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Line implements Serializable {

    private final int length;

    private final String geometry;

    public LineJson toLineJson(){
        String[] coordinates = geometry.replaceAll("\"type\":\"LineString\",\"coordinates\":","")
                .replace("[","").replace("]","")
                .replace("{","").replace("}","")
                .split(",");
        List<Double> i = Arrays.stream(coordinates).map(Double::valueOf).collect(Collectors.toUnmodifiableList());
        return new LineJson(length, i);
    }
}
