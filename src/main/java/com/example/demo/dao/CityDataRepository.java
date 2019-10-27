package com.example.demo.dao;

import com.example.demo.dto.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityDataRepository extends CrudRepository<City, Integer> {
    City findByCityName(String cityName);
}