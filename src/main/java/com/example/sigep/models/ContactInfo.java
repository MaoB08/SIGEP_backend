package com.example.sigep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTACT_INFO")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id; // ← PK propia

    private String phone;

    @Enumerated(EnumType.STRING)
    private PhoneCompany phoneCompany;

    private String contactCode;

    @ManyToOne
    @JoinColumn(name = "operator_id") // ← FK hacia Operator
    private Operator operator;
}
