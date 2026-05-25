package com.example.sigep.services;

import com.example.sigep.dtos.ContactInfoDTO;
import com.example.sigep.mappers.ContactInfoMapper;
import com.example.sigep.models.ContactInfo;
import com.example.sigep.models.Operator;
import com.example.sigep.repositories.ContactInfoRepository;
import com.example.sigep.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {

    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoService(ContactInfoMapper contactInfoMapper) {
        this.contactInfoMapper = contactInfoMapper;
    }

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
        contactInfo.setContactCode(operator.getOperator_id() + "ph" + (long) operator.getContactInfo().size());
        contactInfo = contactInfoRepository.save(contactInfo);

        return contactInfoMapper.toContactInfoDTO(contactInfo);
    }

    public ContactInfoDTO editContact(String contactCode, ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = contactInfoRepository.findByContactCode(contactCode).orElseThrow();

        contactInfo.setPhone(contactInfoDTO.phone());
        contactInfo.setPhoneCompany(contactInfoDTO.phoneCompany());
        contactInfo.setContactCode(contactInfo.getContactCode());

        contactInfo = contactInfoRepository.save(contactInfo);

        return contactInfoMapper.toContactInfoDTO(contactInfo);
    }
}
