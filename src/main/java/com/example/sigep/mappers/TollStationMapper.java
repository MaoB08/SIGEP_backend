package com.example.sigep.mappers;

import com.example.sigep.dtos.TollStationDTO;
import com.example.sigep.dtos.TollStationFullDTO;
import com.example.sigep.models.TollStation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
            OperatorMapper.class,
                StateMapper.class
        }
)
public interface TollStationMapper {

    TollStationFullDTO toFullDTO(TollStation tollStation);

    List<TollStationDTO> listOfTollStationDTO(List<TollStation> tollStations);

    List<TollStationFullDTO> listOfTollStationFullDTO(List<TollStation> tollStations);
}
