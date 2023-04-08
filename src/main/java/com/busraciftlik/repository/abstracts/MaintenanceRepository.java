package com.busraciftlik.repository.abstracts;

import com.busraciftlik.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
    Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);
    boolean existsByCarIdAndIsCompletedFalse(int carId);

}
