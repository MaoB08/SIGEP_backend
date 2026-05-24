package com.example.sigep.dtos;

import com.example.sigep.models.StatusTollStation;

public record TollStationRequestDTO(
        String name,
        String code,
        int numberOfEmployees,
        StatusTollStation status,
        Long operatorId,
        Long stateId
) {
}
