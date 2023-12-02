package com.bookmyshow.Models;

import com.bookmyshow.Models.Auditorium;
import com.bookmyshow.Models.BaseModel;
import com.bookmyshow.Models.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;
    @OneToMany
    private List<Auditorium> auditoriums;
    @ManyToOne
    private City city;
}
