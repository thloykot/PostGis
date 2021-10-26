package com.example.geometry.model.customObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineCoordinates {

    private List<List<Double>> coordinates;

}
