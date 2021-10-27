package com.example.geometry.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LineEntity {

    private final int length;

    private final String coordinates;
}