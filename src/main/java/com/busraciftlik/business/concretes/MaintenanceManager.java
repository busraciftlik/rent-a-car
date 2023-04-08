package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.CarService;
import com.busraciftlik.business.abstracts.MaintenanceService;
import com.busraciftlik.business.dto.requests.create.CreateMaintenanceRequest;
import com.busraciftlik.business.dto.requests.update.UpdateMaintenanceRequest;
import com.busraciftlik.business.dto.responses.create.CreateMaintenanceResponse;
import com.busraciftlik.business.dto.responses.get.GetAllMaintenancesResponse;
import com.busraciftlik.business.dto.responses.get.GetMaintenanceResponse;
import com.busraciftlik.business.dto.responses.update.UpdateMaintenanceResponse;
import com.busraciftlik.entities.Maintenance;
import com.busraciftlik.entities.enums.State;
import com.busraciftlik.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setDueDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkCarAvailabilityForMaintenance(request.getCarId());
        checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setDueDate(null);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        repository.save(maintenance);

        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);
    }

    private void checkIfMaintenanceExists(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No such maintenance information available!");
        }
    }

    private void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("The car is currently under maintenance!");
        }
    }

    private void checkIfCarIsNotUnderMaintenance(int carId){
        if(!repository.existsByCarIdAndIsCompletedFalse(carId)){
            throw new RuntimeException("No such car found in maintenance!");
        }
    }

    private void checkCarAvailabilityForMaintenance(int carId) {
        if (carService.getById(carId).getState().equals(State.RENTED)) {
            throw new RuntimeException("The car cannot be serviced because it is on rent!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId = repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedFalse(carId)){
            carService.changeState(carId, State.AVAILABLE);
        }
    }
}