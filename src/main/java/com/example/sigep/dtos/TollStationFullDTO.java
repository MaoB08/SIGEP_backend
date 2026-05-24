package com.example.sigep.dtos;

import com.example.sigep.models.Operator;
import com.example.sigep.models.State;
import com.example.sigep.models.StatusTollStation;

public record TollStationFullDTO(
        String name,
        String code,
        int numberOfEmployees,
        StatusTollStation status,
        OperatorDTO operator,
        StateDTO state
) {
}
