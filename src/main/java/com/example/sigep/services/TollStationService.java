package com.example.sigep.services;

import com.example.sigep.dtos.*;
import com.example.sigep.models.Operator;
import com.example.sigep.models.State;
import com.example.sigep.models.TollStation;
import com.example.sigep.repositories.OperatorRepository;
import com.example.sigep.repositories.StateRepository;
import com.example.sigep.repositories.TollStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TollStationService {

    private final TollStationRepository tollStationRepository;

    public TollStationService(TollStationRepository tollStationRepository) {
        this.tollStationRepository = tollStationRepository;
    }

    @Autowired
    OperatorRepository operatorRepository;

    @Autowired
    StateRepository stateRepository;

    public List<TollStationDTO> getOnlyTollStations() {
        return tollStationRepository.findAll().stream().map(toll ->
                new TollStationDTO(
                        toll.getName(),
                        toll.getCode(),
                        toll.getNumberOfEmployees(),
                        toll.getStatus()
                )).toList();
    }

    public List<TollStationFullDTO> getAllTollStations() {
        return tollStationRepository.findAll().stream().map(toll ->
                new TollStationFullDTO(
                        toll.getName(),
                        toll.getCode(),
                        toll.getNumberOfEmployees(),
                        toll.getStatus(),
                        new OperatorDTO(
                                toll.getOperator().getName(),
                                toll.getOperator().getAge(),
                                toll.getOperator().isStatus()
                        ),
                        new StateDTO(
                                toll.getState().getStateName()
                        )
                )).toList();
    }

    public TollStationFullDTO getSpecificTollStation(Long id) {
        TollStation toll = tollStationRepository.findById(id).orElseThrow(()->new RuntimeException("Estacion de peaje no encontrada"));
        return new TollStationFullDTO(
                toll.getName(),
                toll.getCode(),
                toll.getNumberOfEmployees(),
                toll.getStatus(),
                new OperatorDTO(
                        toll.getOperator().getName(),
                        toll.getOperator().getAge(),
                        toll.getOperator().isStatus()
                ),
                new StateDTO(
                        toll.getState().getStateName()
                )
        );
    }

    public TollStationFullDTO createTollStation(TollStationRequestDTO tollStationFullDTO) {

        Operator op = operatorRepository.findById(tollStationFullDTO.operatorId()).orElseThrow(()-> new RuntimeException("No se encontro el operador"));
        State state = stateRepository.findById(tollStationFullDTO.stateId()).orElseThrow(()->new RuntimeException("No se encontro el departamento"));

        TollStation tollStation = new TollStation();
        tollStation.setName(tollStationFullDTO.name());
        tollStation.setCode(tollStationFullDTO.code());
        tollStation.setNumberOfEmployees(tollStationFullDTO.numberOfEmployees());
        tollStation.setStatus(tollStationFullDTO.status());
        tollStation.setOperator(op);
        tollStation.setState(state);
        tollStationRepository.save(tollStation);

        return new TollStationFullDTO(
                tollStation.getName(),
                tollStation.getCode(),
                tollStation.getNumberOfEmployees(),
                tollStation.getStatus(),
                new OperatorDTO(
                        tollStation.getOperator().getName(),
                        tollStation.getOperator().getAge(),
                        tollStation.getOperator().isStatus()
                ),
                new StateDTO(
                        tollStation.getState().getStateName()
                )
        );
    }

    public TollStationFullDTO editTollStation(Long id, TollStationRequestDTO tollStationFullDTO) {
        TollStation tollStation = tollStationRepository.findById(id).orElseThrow(()-> new RuntimeException("no se encontro la estacion de peaje"));
        Operator op = operatorRepository.findById(tollStationFullDTO.operatorId()).orElseThrow(()-> new RuntimeException("No se encontro el operador"));
        State state = stateRepository.findById(tollStationFullDTO.stateId()).orElseThrow(()->new RuntimeException("No se encontro el departamento"));

        tollStation.setName(tollStationFullDTO.name());
        tollStation.setCode(tollStationFullDTO.code());
        tollStation.setNumberOfEmployees(tollStationFullDTO.numberOfEmployees());
        tollStation.setStatus(tollStationFullDTO.status());
        tollStation.setOperator(op);
        tollStation.setState(state);
        tollStationRepository.save(tollStation);
        return new TollStationFullDTO(
                tollStation.getName(),
                tollStation.getCode(),
                tollStation.getNumberOfEmployees(),
                tollStation.getStatus(),
                new OperatorDTO(
                        tollStation.getOperator().getName(),
                        tollStation.getOperator().getAge(),
                        tollStation.getOperator().isStatus()
                ),
                new StateDTO(
                        tollStation.getState().getStateName()
                )
        );
    }

    public ResponseEntity<String> deleteTollStation(Long id) {
        TollStation tollStation = tollStationRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro la estación de peaje"));
        tollStationRepository.delete(tollStation);
        return ResponseEntity.ok("Se ha elimiando de forma correcta la estacion de peaje");
    }
}
