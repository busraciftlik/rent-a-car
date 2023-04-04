package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.CarService;
import com.busraciftlik.business.dto.requests.create.CreateCarRequest;
import com.busraciftlik.business.dto.requests.update.UpdateCarRequest;
import com.busraciftlik.business.dto.responses.create.CreateCarResponse;
import com.busraciftlik.business.dto.responses.get.GetAllCarsResponse;
import com.busraciftlik.business.dto.responses.get.GetCarResponse;
import com.busraciftlik.business.dto.responses.update.UpdateCarResponse;
import com.busraciftlik.entities.Car;
import com.busraciftlik.repository.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapper modelMapper;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> responses = cars
                .stream()
                .map(car -> modelMapper.map(car, GetAllCarsResponse.class))
                .toList();
        return responses;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = modelMapper.map(request, Car.class);
        car.setId(0);
        Car createdCar = carRepository.save(car);
        CreateCarResponse response = modelMapper.map(createdCar, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        Car car = modelMapper.map(request, Car.class);
        car.setId(id);
        carRepository.save(car);
        UpdateCarResponse response = modelMapper.map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse getCarResponse = modelMapper.map(car, GetCarResponse.class);
        return getCarResponse;
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }
}
