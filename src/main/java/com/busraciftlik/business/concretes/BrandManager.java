package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.BrandService;
import com.busraciftlik.business.dto.requests.create.CreateBrandRequest;
import com.busraciftlik.business.dto.requests.update.UpdateBrandRequest;
import com.busraciftlik.business.dto.responses.create.CreateBrandResponse;
import com.busraciftlik.business.dto.responses.get.GetAllBrandsResponse;
import com.busraciftlik.business.dto.responses.get.GetBrandResponse;
import com.busraciftlik.business.dto.responses.update.UpdateBrandResponse;
import com.busraciftlik.entities.Brand;
import com.busraciftlik.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> modelMapper.map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        Brand brand = modelMapper.map(request, Brand.class);
        brand.setId(0);
        brandRepository.save(brand);
        CreateBrandResponse response = modelMapper.map(brand, CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        Brand brand = modelMapper.map(request,Brand.class);
        brand.setId(id);
        brandRepository.save(brand);
        UpdateBrandResponse updateBrandResponse = modelMapper.map(brand,UpdateBrandResponse.class);
        return updateBrandResponse ;
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = modelMapper.map(brand, GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
