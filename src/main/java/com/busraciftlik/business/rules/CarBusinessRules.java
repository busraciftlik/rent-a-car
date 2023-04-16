package com.busraciftlik.business.rules;

import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import com.busraciftlik.repository.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Car.notExists);
        }
    }
}
