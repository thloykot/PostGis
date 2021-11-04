package com.example.geometry.model.customObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineCoordinates {

    private final List<List<Double>> coordinates;

    public LineCoordinates(@JsonProperty("coordinates") List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Point> getPoints() {
        return coordinates.stream().filter(doubles -> doubles.size() == 2)
                .map(doubles -> new Point(doubles.get(0), doubles.get(1))).collect(Collectors.toUnmodifiableList());
    }

}
