package com.busraciftlik.repository.abstracts;

import com.busraciftlik.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
