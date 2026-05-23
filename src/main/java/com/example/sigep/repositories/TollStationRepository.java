package com.example.sigep.repositories;

import com.example.sigep.models.TollStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TollStationRepository extends JpaRepository<TollStation,Long> {
}
