package com.example.geometry.model;

import com.example.geometry.model.customObject.MyCustomObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class Line implements Serializable {

    private final int length;

    private final MyCustomObject geometry;

    @SneakyThrows
    public Line(LinePoJo linePoJo){
        this.length = linePoJo.getLength();
        this.geometry = new ObjectMapper().readValue(linePoJo.getGeometry(),MyCustomObject.class);
    }
}
