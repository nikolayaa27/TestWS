package com.example.demo.dao;

import com.example.demo.dto.entities.WeatherData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Integer> {

    @Query("select data from WeatherData data where data.city.cityName=:city_name and data.weatherDataDate=:weather_data_date")
    List<WeatherData> fetchWeatherData(@Param("city_name") String city_name, @Param("weather_data_date") LocalDate weather_data_date);
}