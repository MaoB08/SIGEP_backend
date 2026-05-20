package com.example.sigep.controllers;

import com.example.sigep.dtos.OperatorDTO;
import com.example.sigep.dtos.OperatorDTORequest;
import com.example.sigep.dtos.OperatorFullDTO;
import com.example.sigep.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    OperatorService operatorService;
    @GetMapping("/all")
    public List<OperatorDTO> getAllOperators(){
        return operatorService.getAllOperators();
    }

    @GetMapping("/contact")
    public List<OperatorFullDTO> getAllOperatorsWithContactInfo(){
        return operatorService.getAllOperatorsWithContactInfo();
    }
    @GetMapping("/{id}")
    public List<OperatorDTO> getOperatorById(@PathVariable Long id){
        return operatorService.getOperatorById(id);
    }

    @GetMapping("/name/{name}")
    public List<OperatorDTO> getOperatorByName(@PathVariable String name){
        return operatorService.getOperatorByName(name);
    }

    @PostMapping("/create")
    public OperatorDTO createOperator(@RequestBody OperatorDTORequest operatorDTORequest){
        return operatorService.saveOperator(operatorDTORequest);
    }

    @PostMapping("/edit/{id}")
    public OperatorFullDTO editOperator(@PathVariable Long id, @RequestBody OperatorDTO operatorDTO){
        return operatorService.editOperator(id,operatorDTO);
    }
}
