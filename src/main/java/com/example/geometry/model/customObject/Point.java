package com.example.geometry.model.customObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Point {

    private final double x;

    private final double y;

    @Override
    public String toString() {
        return x + " " + y;
    }
}
