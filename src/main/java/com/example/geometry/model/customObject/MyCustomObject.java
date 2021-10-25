package com.example.geometry.model.customObject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MyCustomObject implements Serializable {

    private String type;

    private Double[][] coordinates;

}
