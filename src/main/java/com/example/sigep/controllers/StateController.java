package com.example.sigep.controllers;

import com.example.sigep.dtos.StateDTO;
import com.example.sigep.services.CityService;
import com.example.sigep.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping
    public List<StateDTO> obtenerDepartamentos(){
        return stateService.obtenerDepartamentos();
    }

    @GetMapping("/{id}")
    public StateDTO obtenerDepartamentoEnEspecifico(@PathVariable Long id){
        return stateService.obtenerDepartamentoEnEspecifico(id);
    }
}
