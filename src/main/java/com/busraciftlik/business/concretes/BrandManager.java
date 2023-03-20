package com.busraciftlik.business.concretes;

import com.busraciftlik.business.abstracts.BrandService;
import com.busraciftlik.entities.concretes.Brand;
import com.busraciftlik.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.add(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        return brandRepository.update(id, brand);
    }

    @Override
    public Brand getById(int id) {
        return brandRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        brandRepository.delete(id);

    }
}
