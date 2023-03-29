package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateModelRequest;
import com.busraciftlik.business.dto.requests.update.UpdateModelRequest;
import com.busraciftlik.business.dto.responses.create.CreateModelResponse;
import com.busraciftlik.business.dto.responses.get.GetAllModelsResponse;
import com.busraciftlik.business.dto.responses.get.GetModelResponse;
import com.busraciftlik.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(int id, UpdateModelRequest request);

    GetModelResponse getById(int id);

    void delete(int id);
}
