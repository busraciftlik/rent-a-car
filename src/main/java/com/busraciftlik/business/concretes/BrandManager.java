package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.BrandService;
import com.busraciftlik.business.dto.requests.create.CreateBrandRequest;
import com.busraciftlik.business.dto.requests.update.UpdateBrandRequest;
import com.busraciftlik.business.dto.responses.create.CreateBrandResponse;
import com.busraciftlik.business.dto.responses.get.GetAllBrandsResponse;
import com.busraciftlik.business.dto.responses.get.GetBrandResponse;
import com.busraciftlik.business.dto.responses.update.UpdateBrandResponse;
import com.busraciftlik.business.rules.BrandBusinessRules;
import com.busraciftlik.entities.Brand;
import com.busraciftlik.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final BrandBusinessRules rules;

    @Override
    @Cacheable(value = "brand_list")
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> modelMapper.map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;
    }

    @Override
    @CacheEvict(value = "brand_list",allEntries = true)
    public CreateBrandResponse add(CreateBrandRequest request) {
        rules.checkIfBrandExistsByName(request.getName());
        Brand brand = modelMapper.map(request, Brand.class);
        brand.setId(0);
        Brand createdBrand = brandRepository.save(brand);
        CreateBrandResponse response = modelMapper.map(createdBrand, CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        rules.checkIfBrandExists(id);
        Brand brand = modelMapper.map(request, Brand.class);
        brand.setId(id);
        brandRepository.save(brand);
        UpdateBrandResponse updateBrandResponse = modelMapper.map(brand, UpdateBrandResponse.class);
        return updateBrandResponse;
    }

    @Override
    public GetBrandResponse getById(int id) {
        rules.checkIfBrandExists(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = modelMapper.map(brand, GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public void delete(int id) {
        rules.checkIfBrandExists(id);
        brandRepository.deleteById(id);
    }
}
