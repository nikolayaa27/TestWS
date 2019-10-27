package com.example.demo.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather_conditions_t")
@Getter
@Setter
public class WeatherConditions {

    @Id
    @GeneratedValue
    @Column(name = "weather_condition_id")
    private int weatherConditionID;

    @Column(name = "weather_condition_temperature")
    private int temperature;

    @Column(name = "weather_condition_humidity")
    private int humidity;

    @JsonBackReference
    @OneToMany(mappedBy = "weatherConditions", cascade = CascadeType.PERSIST)
    private Set<WeatherData> weatherDataSet;
}