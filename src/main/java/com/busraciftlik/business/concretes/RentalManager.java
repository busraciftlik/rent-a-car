package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.CarService;
import com.busraciftlik.business.abstracts.RentalService;
import com.busraciftlik.business.dto.requests.create.CreateRentalRequest;
import com.busraciftlik.business.dto.requests.update.UpdateRentalRequest;
import com.busraciftlik.business.dto.responses.create.CreateRentalResponse;
import com.busraciftlik.business.dto.responses.get.GetAllRentalsResponse;
import com.busraciftlik.business.dto.responses.get.GetRentalResponse;
import com.busraciftlik.business.dto.responses.update.UpdateRentalResponse;
import com.busraciftlik.entities.Rental;
import com.busraciftlik.entities.enums.State;
import com.busraciftlik.repository.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalsResponse> getAllRentalsResponses = rentals
                .stream()
                .map(rental -> modelMapper.map(rental, GetAllRentalsResponse.class))
                .toList();

        return getAllRentalsResponses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExists(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse = modelMapper.map(rental, GetRentalResponse.class);

        return getRentalResponse;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        checkIfCarAvailable(request.getCarId());
        Rental rental = modelMapper.map(request, Rental.class);
        rental.setId(0);
        rental.setStartDate(LocalDateTime.now());
        rental.setTotalPrice(getTotalPrice(rental));
        rentalRepository.save(rental);
        carService.changeState(rental.getCar().getId(), State.RENTED);
       CreateRentalResponse createRentalResponse = modelMapper.map(rental, CreateRentalResponse.class);
        return createRentalResponse;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        checkIfRentalExists(id);
        Rental rental = modelMapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        rentalRepository.save(rental);
        UpdateRentalResponse updateRentalResponse = modelMapper.map(rental, UpdateRentalResponse.class);

        return updateRentalResponse;
    }

    @Override
    public void delete(int id) {
        checkIfRentalExists(id);
        int carId = rentalRepository.findById(id).get().getId();
        carService.changeState(carId,State.AVAILABLE);
        rentalRepository.deleteById(id);

    }

    private void checkIfRentalExists(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new RuntimeException("No such car found!");
        }
    }
    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }
    private void checkIfCarAvailable(int carId) {
        if(!carService.getById(carId).getState().equals(State.AVAILABLE)){
            throw new RuntimeException("The car is not available!");
        }
    }
}
