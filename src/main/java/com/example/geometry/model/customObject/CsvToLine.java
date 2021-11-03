package com.example.geometry.model.customObject;

import com.example.geometry.dto.LineDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CsvToLine {

    private final ObjectMapper objectMapper;

    public List<LineDto> read(String csv) throws IOException {
        System.out.println(csv);
        String c = csv.replace("id,length,ST_AsGeoJSON(geometry)\n", "");
        StringTokenizer st = new StringTokenizer(c, ",");
        String[] d = c.split("\n");
        return Arrays.stream(d).map(string -> {
            String s = string.replace("\"", "").replace("}", "")
                    .replace("{", "").replaceAll("type:LineString,coordinates:", "");
            String[] array = s.split(",");
            return makeLine(array);
        }).collect(Collectors.toUnmodifiableList());
    }

    private LineDto makeLine(String[] data) {
        if (data.length == 0) {
            log.error("Error parsing csv backup file!");
            return null;
        }
        List<String> strings = Arrays.stream(data).collect(Collectors.toList());
        strings.remove(0);
        strings.remove(0);
        return new LineDto(Integer.parseInt(data[0]), Integer.parseInt(data[1]), makePointsList(strings));
    }

    private List<Point> makePointsList(List<String> coordinates) {
        String s = "{\"coordinates\":" + coordinates.stream().collect(Collectors.joining(",")) + "}";
        String c = "{\"type\":\"LineString\",\"coordinates\":[[7.4,5],[2.5,5.6]]}";
        try {
            LineCoordinates d = objectMapper.readValue(s, LineCoordinates.class);
            return d.getCoordinates().stream().filter(doubles -> doubles.size() == 2)
                    .map(doubles -> new Point(doubles.get(0), doubles.get(1))).collect(Collectors.toUnmodifiableList());
        } catch (JsonProcessingException e) {
            log.error("Error parsing", e);
            return null;
        }
    }
}
