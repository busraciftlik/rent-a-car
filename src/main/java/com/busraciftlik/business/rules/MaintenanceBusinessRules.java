package com.busraciftlik.business.rules;

import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import com.busraciftlik.entities.enums.State;
import com.busraciftlik.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Maintenance.notExists);
        }
    }

    public void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.carExists);
        }
    }

    public void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new BusinessException(Messages.Maintenance.carNotExists);
        }
    }

    public void checkCarAvailabilityForMaintenance(State state) {
        if (!state.equals(State.RENTED)) {
            throw new BusinessException(Messages.Maintenance.carIsRented);
        }
    }

}
