package com.example.geometry.lineDB.converter;

import org.jooq.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeoJsonToList implements Converter<String, List> {
    @Override
    public List<Double> from(String geometry) {
        String[] coordinates = geometry.replaceAll("\"type\":\"LineString\",\"coordinates\":", "")
                .replace("[", "").replace("]", "")
                .replace("{", "").replace("}", "")
                .split(",");
        return Arrays.stream(coordinates).map(Double::valueOf).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String to(List doubles) {
        List<String> geoJsonString = new ArrayList<>();
        for (int i = 0; i < doubles.size(); i++) {
            if (i % 2 == 0) {
                geoJsonString.add("[" + doubles.get(i));
            } else {
                geoJsonString.add(doubles.get(i) + "]");
            }
        }
        return "{\"type\":\"LineString\", \"coordinates\":" + geoJsonString + "}";
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<List> toType() {
        return List.class;
    }
}
