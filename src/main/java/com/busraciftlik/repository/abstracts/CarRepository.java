package com.busraciftlik.repository.abstracts;

import com.busraciftlik.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
