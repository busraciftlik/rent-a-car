package com.busraciftlik.api.controllers;


import com.busraciftlik.business.abstracts.BrandService;
import com.busraciftlik.business.dto.requests.create.CreateBrandRequest;
import com.busraciftlik.business.dto.requests.update.UpdateBrandRequest;
import com.busraciftlik.business.dto.responses.create.CreateBrandResponse;
import com.busraciftlik.business.dto.responses.get.GetAllBrandsResponse;
import com.busraciftlik.business.dto.responses.get.GetBrandResponse;
import com.busraciftlik.business.dto.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping
    public List<GetAllBrandsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

