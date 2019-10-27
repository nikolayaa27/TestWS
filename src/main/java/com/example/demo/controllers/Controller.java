package com.example.demo.controllers;

import com.example.demo.dao.CityDataRepository;
import com.example.demo.dao.ConditionsDataRepository;
import com.example.demo.dao.UserDataRepository;
import com.example.demo.dao.WeatherDataRepository;
import com.example.demo.dto.RequestData;
import com.example.demo.dto.SaveData;
import com.example.demo.dto.entities.City;
import com.example.demo.dto.entities.User;
import com.example.demo.dto.entities.WeatherConditions;
import com.example.demo.dto.entities.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
public class Controller {


    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    CityDataRepository cityDataRepository;

    @Autowired
    ConditionsDataRepository conditionsDataRepository;

    @GetMapping("/api/retrieve")
    public ResponseEntity retrieveData(@RequestParam String req) {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestData  requestData;
        try {
            objectMapper.registerModule(new JavaTimeModule());
            requestData = objectMapper.readValue(req, RequestData.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity("Bad Input Data", HttpStatus.BAD_REQUEST);
        }

        User user = userDataRepository.findByUserLoginAndUserPassword(requestData.getUser(), requestData.getPass());
        if (user == null) { return new ResponseEntity("Check your user credentials", HttpStatus.UNAUTHORIZED); }
        if (!user.isUserAdminFlag()) {
            return new ResponseEntity("Sorry but you suppose to be an admin", HttpStatus.UNAUTHORIZED);
        }

        List<WeatherData> weatherData;
        weatherData = weatherDataRepository.fetchWeatherData(requestData.getCity(), requestData.getDate());

        return weatherData.size() > 0 ? new ResponseEntity(weatherData, HttpStatus.OK) : new ResponseEntity("Data not found", HttpStatus.OK);
    }

    @PostMapping("/api/save")
    public ResponseEntity saveWeather(@RequestBody SaveData saveData) {


        User user = userDataRepository.findByUserLoginAndUserPassword(saveData.getUser(), saveData.getPass());
        if (user == null) { return new ResponseEntity("Check your user credentials", HttpStatus.UNAUTHORIZED); }
        if (!user.isUserAdminFlag()) {
            return new ResponseEntity("Sorry but you suppose to be an admin", HttpStatus.UNAUTHORIZED);
        }

        // save or update data to prevent PK constraint violation
        City city = cityDataRepository.findByCityName(saveData.getCity());
        if (city == null) {
            city = new City();
            city.setCityName(saveData.getCity());
        }




        // this data is always new
        WeatherConditions weatherConditions = new WeatherConditions();
        weatherConditions.setTemperature(saveData.getConditions().getTemperature());
        weatherConditions.setHumidity(saveData.getConditions().getHumidity());

        // prepare the full Entity
        WeatherData weatherData = new WeatherData();
        weatherData.setWeatherDataDate(LocalDate.now());
        weatherData.setCity(city);
        weatherData.setWeatherConditions(weatherConditions);
        weatherData.setUser(user);

        weatherDataRepository.save(weatherData);

        return new ResponseEntity("Data Saved!", HttpStatus.OK);
    }


}