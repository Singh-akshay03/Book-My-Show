package com.bookmyshow.Models;

import com.bookmyshow.Models.Actor;
import com.bookmyshow.Models.BaseModel;
import com.bookmyshow.Models.Feature;
import com.bookmyshow.Models.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    @OneToMany
    private List<Actor> actors;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> movieFeatures;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Genre> genre;
}
