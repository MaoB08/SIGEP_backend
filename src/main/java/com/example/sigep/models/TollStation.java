package com.example.sigep.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "toll_station")
public class TollStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;

    private String name;
    private String code;
    private int numberOfEmployees;

    @Enumerated(EnumType.STRING)
    private StatusTollStation status;


    @ManyToOne(fetch = FetchType.LAZY)
    private Operator operator;

    @ManyToOne
    private State state;
}
