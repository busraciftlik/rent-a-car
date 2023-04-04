package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.CarService;
import com.busraciftlik.business.abstracts.MaintenanceService;
import com.busraciftlik.business.dto.requests.create.CreateMaintenanceRequest;
import com.busraciftlik.business.dto.requests.update.UpdateCarRequest;
import com.busraciftlik.business.dto.requests.update.UpdateMaintenanceRequest;
import com.busraciftlik.business.dto.responses.create.CreateMaintenanceResponse;
import com.busraciftlik.business.dto.responses.get.GetAllMaintenancesResponse;
import com.busraciftlik.business.dto.responses.get.GetCarResponse;
import com.busraciftlik.business.dto.responses.get.GetMaintenanceResponse;
import com.busraciftlik.business.dto.responses.update.UpdateMaintenanceResponse;
import com.busraciftlik.entities.Maintenance;
import com.busraciftlik.entities.enums.State;
import com.busraciftlik.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {

    private MaintenanceRepository repository;
    private CarService carService;
    private ModelMapper mapper;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> responses = maintenances
                .stream()
                .map(car -> mapper.map(car, GetAllMaintenancesResponse.class)).toList();
        return responses;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance,GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request,Maintenance.class);
        maintenance.setId(0);
        checkIfCarCanBeSendToMaintenance(maintenance.getCar().getId());
        changeCarStateToMaintenance(maintenance.getCar().getId());
        Maintenance createMaintenance = repository.save(maintenance);
        CreateMaintenanceResponse response = mapper.map(createMaintenance,CreateMaintenanceResponse.class);
        return response;

    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request,Maintenance.class);
        maintenance.setId(id);
        if (maintenance.getDueDate().before(new Date())) {
            changeCarStateToAvailable(maintenance.getCar().getId());
        }
        Maintenance updateMaintenance = repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(updateMaintenance,UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
    //business rules

    private void changeCarStateToMaintenance(int carId){
        GetCarResponse carResponse = carService.getById(carId);
        carResponse.setState(State.MAINTENANCE);

        UpdateCarRequest updateCarRequest = mapper.map(carResponse, UpdateCarRequest.class);
        carService.update(carResponse.getId(),updateCarRequest);
    }

    private void changeCarStateToAvailable(int carId) {
        GetCarResponse carResponse = carService.getById(carId);
        carResponse.setState(State.AVAILABLE);

        UpdateCarRequest updateCarRequest = mapper.map(carResponse, UpdateCarRequest.class);
        carService.update(carId, updateCarRequest);
    }

    private void checkIfCarCanBeSendToMaintenance(int carId){
        checkIfCarStateMaintenance(carId);
        checkIfCarStateRented(carId);
    }

    private void checkIfCarStateRented(int carId) {
        GetCarResponse car = carService.getById(carId);
        if (car.getState()==State.RENTED) {
            throw new RuntimeException("The car is rented, cannot be sent for maintenance.");
        }
    }
    private void checkIfCarStateMaintenance(int carId) {
        GetCarResponse car = carService.getById(carId);
        if (car.getState()==(State.MAINTENANCE)) {
            throw new RuntimeException("A car under maintenance cannot be sent for maintenance.");
        }
    }

}