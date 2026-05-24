package com.example.sigep.services;

import com.example.sigep.dtos.CityDTO;
import com.example.sigep.dtos.StateDTO;
import com.example.sigep.models.City;
import com.example.sigep.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDTO obtenerCiudadEnEspecifico(Long id) {
        City city = cityRepository.findById(id).orElseThrow();
        return new CityDTO(
                city.getCityName(),
                new StateDTO(
                        city.getState().getStateName()
                ));
    }

    public List<CityDTO> obtenerCiudades() {
        return cityRepository.findAll().stream().map(city ->
                new CityDTO(
                        city.getCityName(),
                        new StateDTO(
                                city.getState().getStateName()
                        )
                )).toList();
    }
}
