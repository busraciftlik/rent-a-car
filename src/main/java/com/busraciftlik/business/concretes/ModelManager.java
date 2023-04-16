package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.ModelService;
import com.busraciftlik.business.dto.requests.create.CreateModelRequest;
import com.busraciftlik.business.dto.requests.update.UpdateModelRequest;
import com.busraciftlik.business.dto.responses.create.CreateModelResponse;
import com.busraciftlik.business.dto.responses.get.GetAllModelsResponse;
import com.busraciftlik.business.dto.responses.get.GetModelResponse;
import com.busraciftlik.business.dto.responses.update.UpdateModelResponse;
import com.busraciftlik.business.rules.ModelBusinessRules;
import com.busraciftlik.entities.Model;
import com.busraciftlik.repository.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private ModelMapper modelMapper;
    private final ModelBusinessRules rules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelsResponse> response = models
                .stream()
                .map(model -> modelMapper.map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        rules.checkIfModelExists(id);
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse response = modelMapper.map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = modelMapper.map(request, Model.class);
        model.setId(0);
        modelRepository.save(model);
        CreateModelResponse response = modelMapper.map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        rules.checkIfModelExists(id);
        Model model = modelMapper.map(request, Model.class);
        model.setId(id);
        modelRepository.save(model);
        UpdateModelResponse response = modelMapper.map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfModelExists(id);
        modelRepository.deleteById(id);
    }


}