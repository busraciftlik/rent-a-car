package com.busraciftlik.business.rules;

import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import com.busraciftlik.entities.Rental;
import com.busraciftlik.entities.enums.State;
import com.busraciftlik.repository.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    public void checkIfRentalExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Rental.notExists);
        }
    }

    public void checkIfCarAvailable(State state) {
        if (!state.equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Car.carNotAvailable);
        }
    }
}
