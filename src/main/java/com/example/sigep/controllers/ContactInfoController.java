package com.example.sigep.controllers;

import com.example.sigep.dtos.ContactInfoDTO;
import com.example.sigep.services.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    @PostMapping("/edit/{code}")
    public ContactInfoDTO editarContact(@PathVariable String code, @RequestBody ContactInfoDTO contactInfoDTO){
        return contactInfoService.editContact(code, contactInfoDTO);
    }

    @PostMapping("/add/{id}")
    public ContactInfoDTO añadirContact(@PathVariable Long id, @RequestBody ContactInfoDTO contactInfoDTO){
        return contactInfoService.addContact(id, contactInfoDTO);
    }
}
