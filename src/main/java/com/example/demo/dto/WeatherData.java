package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather_data")
@Getter
@Setter
public class WeatherData {
    @Id
    @Column(name = "weather_data_id")
    @GeneratedValue
    private int weatherDataId;

    @Column(name = "weather_data_date")
    private LocalDateTime weatherDataDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_name")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_condition_id")
    private WeatherConditions weatherConditions;

    @Override
    public String toString() {
        return "weather_data_id=" + weatherDataId + " weather_data_date=" + weatherDataDate + " city_name=" + city.getCityName() + " user_id=" + user.getUserLogin() + " weatherConditions=" + weatherConditions.getHumidity() + " " + weatherConditions.getTemperature();
    }
}

