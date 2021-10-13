package com.example.geometry.model;

import lombok.*;

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

    private String geometry;

}
