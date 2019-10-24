package com.example.demo.controllers;

import com.example.demo.dao.WeatherDataRepository;
import com.example.demo.dto.City;
import com.example.demo.dto.User;
import com.example.demo.dto.WeatherConditions;
import com.example.demo.dto.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class Controller {


    @Autowired
    WeatherDataRepository weatherDataRepository;

    @GetMapping("/api/city/{city}/date/{date}")
    public String retrieveData() {
        List<WeatherData> weatherData;

        LocalDateTime dateTime = LocalDateTime.parse("2019-10-24T11:19:47");
        weatherData = weatherDataRepository.fetchWeatherData("Moscow", dateTime);

        return weatherData.get(0).toString();
    }

    @GetMapping("/api/save")
    public void saveEmployee() {
        City city = new City();
        city.setCityName("Moscow");

        City city1 = new City();
        city1.setCityName("Yekaterinburg");

        WeatherConditions weatherConditions = new WeatherConditions();
        weatherConditions.setTemperature(28);
        weatherConditions.setHumidity(70);

        WeatherConditions weatherConditions1 = new WeatherConditions();
        weatherConditions1.setTemperature(36);
        weatherConditions1.setHumidity(40);

        User user0 = new User();
        user0.setUserLogin("nik");
        user0.setUserPassword("pass");
        user0.setUserAdminFlag(true);

        User user1 = new User();
        user1.setUserLogin("zel");
        user1.setUserPassword("pass");
        user1.setUserAdminFlag(false);

        WeatherData weatherData0 = new WeatherData();
        weatherData0.setWeatherDataDate(LocalDateTime.now());
        weatherData0.setCity(city);
        weatherData0.setWeatherConditions(weatherConditions);
        weatherData0.setUser(user0);

        WeatherData weatherData1 = new WeatherData();
        weatherData1.setWeatherDataDate(LocalDateTime.now());
        weatherData1.setCity(city1);
        weatherData1.setWeatherConditions(weatherConditions1);
        weatherData1.setUser(user1);

//        Set<WeatherData> weatherDataSet = new HashSet();
//        weatherDataSet.add(weatherData0);
//        weatherDataSet.add(weatherData1);

        weatherDataRepository.save(weatherData0);
        weatherDataRepository.save(weatherData1);
    }

}
