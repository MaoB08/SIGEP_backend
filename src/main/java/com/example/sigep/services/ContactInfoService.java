package com.example.sigep.services;

import com.example.sigep.dtos.ContactInfoDTO;
import com.example.sigep.models.ContactInfo;
import com.example.sigep.models.Operator;
import com.example.sigep.repositories.ContactInfoRepository;
import com.example.sigep.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    public ContactInfoDTO addContact(Long operatorId, ContactInfoDTO contactInfoDTO) {
        Operator operator = operatorRepository.findById(operatorId).orElseThrow();

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone(contactInfoDTO.phone());
        contactInfo.setPhoneCompany(contactInfoDTO.phoneCompany());
        contactInfo.setOperator(operator);

        contactInfo = contactInfoRepository.save(contactInfo);
        contactInfo.setContactCode(operator.getOperator_id() + "ph" + contactInfo.getContact_id() + 1);
        contactInfo = contactInfoRepository.save(contactInfo);

        return new ContactInfoDTO(
                contactInfo.getPhone(),
                contactInfo.getPhoneCompany(),
                contactInfo.getContactCode()
        );
    }

    public ContactInfoDTO editContact(String contactCode, ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = contactInfoRepository.findByContactCode(contactCode).orElseThrow();

        contactInfo.setPhone(contactInfoDTO.phone());
        contactInfo.setPhoneCompany(contactInfoDTO.phoneCompany());
        contactInfo.setContactCode(contactInfo.getOperator().getOperator_id() + "ph" + contactInfo.getContact_id());

        contactInfo = contactInfoRepository.save(contactInfo);

        return new ContactInfoDTO(
                contactInfo.getPhone(),
                contactInfo.getPhoneCompany(),
                contactInfo.getContactCode()
        );
    }
}
