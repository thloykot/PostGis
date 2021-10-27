package com.example.geometry.model.customObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineCoordinates {

    private List<List<Double>> coordinates;

    public List<Point> getCoordinates() {
        return coordinates.stream().map(doubles ->
                new Point(doubles.get(0), doubles.get(1))).collect(Collectors.toList());
    }

}
