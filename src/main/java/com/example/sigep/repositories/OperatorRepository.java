package com.example.sigep.repositories;

import com.example.sigep.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Long> {
    Collection<Operator> findByName(String name);

}
