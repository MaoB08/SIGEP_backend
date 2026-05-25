package com.example.sigep.mappers;

import com.example.sigep.dtos.StateDTO;
import com.example.sigep.models.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StateMapper {
    @Mapping(source = "stateName", target = "name")
    StateDTO StateToStateDTO(State state);
}
