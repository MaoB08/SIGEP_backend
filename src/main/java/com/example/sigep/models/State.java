package com.example.sigep.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "state_name")
    private String stateName;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<City> cities = new ArrayList<>();

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<TollStation> tollStations = new ArrayList<>();
}
