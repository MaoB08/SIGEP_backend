package com.example.sigep.controllers;

import com.example.sigep.dtos.TollStationRequestDTO;
import com.example.sigep.dtos.TollStationDTO;
import com.example.sigep.dtos.TollStationFullDTO;
import com.example.sigep.services.TollStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toll-stations")
public class TollStationController {

    @Autowired
    TollStationService tollStationService;

    @GetMapping()
    public List<TollStationDTO> getOnlyTollStations(){
        return tollStationService.getOnlyTollStations();
    }

    @GetMapping("/all")
    public List<TollStationFullDTO> getAllTollStations(){
        return tollStationService.getAllTollStations();
    }

    @GetMapping("/{id}")
    public TollStationFullDTO getSpecificTollStation(@PathVariable Long id){
        return tollStationService.getSpecificTollStation(id);
    }
    @PostMapping()
    public TollStationFullDTO createTollStation(@RequestBody TollStationRequestDTO tollStationFullDTO){
        return tollStationService.createTollStation(tollStationFullDTO);
    }
    @PutMapping("/{id}")
    public TollStationFullDTO editTollStation(@PathVariable Long id, @RequestBody TollStationRequestDTO tollStationFullDTO ){
        return tollStationService.editTollStation(id,tollStationFullDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTollStation(@PathVariable Long id){
        return tollStationService.deleteTollStation(id);
    }

}
