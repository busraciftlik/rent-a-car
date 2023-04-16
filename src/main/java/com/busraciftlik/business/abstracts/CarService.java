package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateCarRequest;
import com.busraciftlik.business.dto.requests.update.UpdateCarRequest;
import com.busraciftlik.business.dto.responses.create.CreateCarResponse;
import com.busraciftlik.business.dto.responses.get.GetAllCarsResponse;
import com.busraciftlik.business.dto.responses.get.GetCarResponse;
import com.busraciftlik.business.dto.responses.update.UpdateCarResponse;
import com.busraciftlik.entities.enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);

    GetCarResponse getById(int id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(int id, UpdateCarRequest request);

    void delete(int id);

    void changeState(int carId, State state);
}
