package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestData {
    private LocalDate date;
    private String    city;
    private String    user;
    private String    pass;

    RequestData(String date, String city, String user, String pass) {
        this.city = city;
        this.pass = pass;
        this.user = user;
        this.date = LocalDate.parse(date);
    }
}