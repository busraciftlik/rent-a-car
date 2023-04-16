package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateRentalRequest;
import com.busraciftlik.business.dto.requests.update.UpdateRentalRequest;
import com.busraciftlik.business.dto.responses.create.CreateRentalResponse;
import com.busraciftlik.business.dto.responses.get.GetAllRentalsResponse;
import com.busraciftlik.business.dto.responses.get.GetRentalResponse;
import com.busraciftlik.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();

    GetRentalResponse getById(int id);

    CreateRentalResponse add(CreateRentalRequest request);

    UpdateRentalResponse update(int id, UpdateRentalRequest request);

    void delete(int id);
}
