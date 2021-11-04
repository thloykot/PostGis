package com.example.geometry.model.customObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineCoordinates {

    List<List<Double>> coordinates;

    public LineCoordinates(@JsonProperty("coordinates") List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }
}
