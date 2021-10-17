package com.example.geometry.model;

import com.vividsolutions.jts.geom.Geometry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Line implements Serializable {

    @Id
    private int id;

    private int length;

    private Geometry geometry;
}
