package com.bookmyshow.Models;

import com.bookmyshow.Models.Auditorium;
import com.bookmyshow.Models.BaseModel;
import com.bookmyshow.Models.Feature;
import com.bookmyshow.Models.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Auditorium auditorium;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> showFeatures;
}
