package com.busraciftlik.api.controllers;

import com.busraciftlik.business.abstracts.ModelService;
import com.busraciftlik.business.dto.requests.create.CreateModelRequest;
import com.busraciftlik.business.dto.requests.update.UpdateModelRequest;
import com.busraciftlik.business.dto.responses.create.CreateModelResponse;
import com.busraciftlik.business.dto.responses.get.GetAllModelsResponse;
import com.busraciftlik.business.dto.responses.get.GetModelResponse;
import com.busraciftlik.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
