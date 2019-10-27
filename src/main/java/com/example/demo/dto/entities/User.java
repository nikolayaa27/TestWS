package com.example.demo.dto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "user_t")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "user_id")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_admin")
    private boolean userAdminFlag;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<WeatherData> weatherDataSet;
}