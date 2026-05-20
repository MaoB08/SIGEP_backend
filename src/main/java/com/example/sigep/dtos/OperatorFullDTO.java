package com.example.sigep.dtos;

import com.example.sigep.models.ContactInfo;

import java.util.List;

public record OperatorFullDTO(
        String name,
        int age,
        boolean state,
        List<ContactInfoDTO> contactInfo
) {
}
