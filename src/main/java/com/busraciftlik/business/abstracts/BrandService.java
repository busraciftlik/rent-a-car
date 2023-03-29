package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateBrandRequest;
import com.busraciftlik.business.dto.requests.update.UpdateBrandRequest;
import com.busraciftlik.business.dto.responses.create.CreateBrandResponse;
import com.busraciftlik.business.dto.responses.get.GetAllBrandsResponse;
import com.busraciftlik.business.dto.responses.get.GetBrandResponse;
import com.busraciftlik.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;


public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(int id, UpdateBrandRequest request);

    GetBrandResponse getById(int id);

    void delete(int id);


}
