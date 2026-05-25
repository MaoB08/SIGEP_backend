package com.example.sigep.mappers;

import com.example.sigep.dtos.OperatorDTO;
import com.example.sigep.dtos.OperatorFullDTO;
import com.example.sigep.models.Operator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
    uses = {
        ContactInfoMapper.class
    }
)
public interface OperatorMapper {

    OperatorDTO OpertorToOperatorDTO(Operator operator);

    List<OperatorFullDTO> listOfFullOperator(List<Operator> operators);

    OperatorFullDTO OpertorToOperatorFullDTO(Operator operator);
}
