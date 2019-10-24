package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city_t")
@Getter
@Setter
public class City {

    @Id
    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<WeatherData> weatherDataSet;
}
