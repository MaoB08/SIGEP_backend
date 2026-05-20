package com.example.sigep.services;

import com.example.sigep.dtos.ContactInfoDTO;
import com.example.sigep.dtos.OperatorDTO;
import com.example.sigep.dtos.OperatorDTORequest;
import com.example.sigep.dtos.OperatorFullDTO;
import com.example.sigep.models.ContactInfo;
import com.example.sigep.models.Operator;
import com.example.sigep.repositories.ContactInfoRepository;
import com.example.sigep.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OperatorService {

    @Autowired
    OperatorRepository operatorRepository;

    @Autowired
    ContactInfoRepository contactInfoRepository;


    public List<OperatorDTO> getAllOperators(){
        return operatorRepository.findAll().stream().map(operator  -> new OperatorDTO(operator.getName(),operator.getAge(),operator.isStatus())).toList();
    }
    public List<OperatorFullDTO> getAllOperatorsWithContactInfo(){
        return operatorRepository.findAll().stream().map(operator ->
                new OperatorFullDTO(
                        operator.getName(),
                        operator.getAge(),
                        operator.isStatus(),
                        operator.getContactInfo()
                                .stream()
                                .map(
                                contactInfo -> new ContactInfoDTO(
                                        contactInfo.getPhone(),
                                        contactInfo.getPhoneCompany(),
                                        contactInfo.getContactCode())).toList()
                )).toList();
    }
    public List<OperatorDTO> getOperatorById(Long id){
        return operatorRepository.findById(id).stream().map(operator -> new OperatorDTO(operator.getName(),operator.getAge(),operator.isStatus())).toList();
    }
    public List<OperatorDTO> getOperatorByName(String name){
        return operatorRepository.findByName(name).stream().map(operator -> new OperatorDTO(operator.getName(),operator.getAge(),operator.isStatus())).toList();
    }

    public OperatorDTO saveOperator(OperatorDTORequest operatorDTORequest) {
        Operator operator = new Operator();
        operator.setName(operatorDTORequest.name());
        operator.setAge(operatorDTORequest.age());
        operator.setStatus(operatorDTORequest.state());

        final Operator operatorRef = operator;

        List<ContactInfo> contacts = operatorDTORequest.contactInfo()
                .stream()
                .map(contactInfoDTO -> {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setPhone(contactInfoDTO.phone());
                    contactInfo.setPhoneCompany(contactInfoDTO.phoneCompany());
                    contactInfo.setOperator(operatorRef);
                    return contactInfo;
                })
                .toList();

        operator.setContactInfo(contacts);
        operator = operatorRepository.save(operator); // primer save: genera IDs

        // segundo paso: actualizar contactCode con los IDs ya generados

        AtomicInteger count = new AtomicInteger(1);

        operator.getContactInfo().forEach(ci ->
                ci.setContactCode(operatorRef.getOperator_id() + "ph" + count.getAndIncrement())
        );
        contactInfoRepository.saveAll(operator.getContactInfo()); // guarda solo los contactos

        return new OperatorDTO(
                operator.getName(),
                operator.getAge(),
                operator.isStatus()
        );
    }

    public OperatorFullDTO editOperator(Long id, OperatorDTO operatorDTO) {
        Operator op = operatorRepository.findById(id).orElseThrow();
        op.setName(operatorDTO.name());
        op.setAge(operatorDTO.age());
        op.setStatus(operatorDTO.state());
        operatorRepository.save(op);
        return new OperatorFullDTO(op.getName(), op.getAge(), op.isStatus(), op.getContactInfo().stream().map(contactInfo -> new ContactInfoDTO(contactInfo.getPhone(), contactInfo.getPhoneCompany(), contactInfo.getContactCode())).toList());
    }

}
