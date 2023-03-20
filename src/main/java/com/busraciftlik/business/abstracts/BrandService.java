package com.busraciftlik.business.abstracts;

import com.busraciftlik.entities.concretes.Brand;

import java.util.List;


public interface BrandService {
    List<Brand> getAll();
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    Brand getById(int id);
    void delete(int id);


}
