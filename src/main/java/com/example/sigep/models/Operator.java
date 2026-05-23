package com.example.sigep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OPERATOR")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operator_id;
    private String name;
    private int age;
    private boolean status;

    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactInfo> contactInfo = new ArrayList<>();

    @OneToMany(mappedBy = "operator",cascade = CascadeType.ALL)
    private List<TollStation> tollStations = new ArrayList<>();
}
