package com.example.sigep.dtos;

import com.example.sigep.models.StatusTollStation;

public record TollStationDTO(
        String name,
        String code,
        int numberOfEmployees,
        StatusTollStation status
) {
}
