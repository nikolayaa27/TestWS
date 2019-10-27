package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveData {
    private LocalDate      date;
    private String         city;
    private String         user;
    private String         pass;
    private SaveConditions conditions;
}