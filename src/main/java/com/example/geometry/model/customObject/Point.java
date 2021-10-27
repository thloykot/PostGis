package com.example.geometry.model.customObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Point {

    private double x;

    private double y;

    @Override
    public String toString() {
        return x + " " + y;
    }
}
