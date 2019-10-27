package com.example.demo.dao;

import com.example.demo.dto.entities.WeatherConditions;
import org.springframework.data.repository.CrudRepository;

public interface ConditionsDataRepository extends CrudRepository<WeatherConditions, Integer> {
}