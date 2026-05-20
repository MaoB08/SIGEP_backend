package com.example.sigep.dtos;

import com.example.sigep.models.PhoneCompany;

public record ContactInfoDTO(
        String phone,
        PhoneCompany phoneCompany,
        String contactCode
) {
}
