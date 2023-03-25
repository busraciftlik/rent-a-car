package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.BrandService;
import com.busraciftlik.entities.concretes.Brand;
import com.busraciftlik.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        brand.setId(id);
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(int id) {
        return brandRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
