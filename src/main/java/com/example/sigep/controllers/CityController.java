package com.example.sigep.controllers;

import com.example.sigep.dtos.CityDTO;
import com.example.sigep.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping
    public List<CityDTO> obtenerCiudades(){
        return cityService.obtenerCiudades();
    }

    @GetMapping("/{id}")
    public CityDTO obtenerCiudadEnEspecifico(@PathVariable Long id){
        return cityService.obtenerCiudadEnEspecifico(id);
    }
}
