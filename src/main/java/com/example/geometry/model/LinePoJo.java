package com.example.geometry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LinePoJo implements Serializable {

    private final int length;

    private final String geometry;
}
