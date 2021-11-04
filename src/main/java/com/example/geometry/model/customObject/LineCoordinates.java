package com.example.geometry.model.customObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineCoordinates {

    private final List<List<Double>> coordinates;

    public LineCoordinates(@JsonProperty("coordinates") List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }
}
