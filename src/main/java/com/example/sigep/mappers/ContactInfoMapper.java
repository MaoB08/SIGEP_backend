package com.example.sigep.mappers;

import com.example.sigep.dtos.ContactInfoDTO;
import com.example.sigep.models.ContactInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfoDTO toContactInfoDTO(ContactInfo contactInfo);

}
