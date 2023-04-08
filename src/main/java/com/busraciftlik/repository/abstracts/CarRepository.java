package com.busraciftlik.repository.abstracts;

import com.busraciftlik.entities.Car;
import com.busraciftlik.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllByStateIsNot(State state);
}
