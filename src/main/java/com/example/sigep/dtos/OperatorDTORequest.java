package com.example.sigep.dtos;

import java.util.List;

public record OperatorDTORequest(
        String name,
        int age,
        boolean state,
        List<ContactInfoDTO> contactInfo
) {
}
