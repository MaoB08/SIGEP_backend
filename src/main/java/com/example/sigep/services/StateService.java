package com.example.sigep.services;

import com.example.sigep.dtos.StateDTO;
import com.example.sigep.models.State;
import com.example.sigep.repositories.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateDTO> obtenerDepartamentos() {
        return stateRepository.findAll().stream().map(state->
                new StateDTO(
                        state.getStateName()
                )
                ).toList();
    }

    public StateDTO obtenerDepartamentoEnEspecifico(Long id) {
        State state = stateRepository.findById(id).orElseThrow();
        return new StateDTO(state.getStateName());
    }
}
