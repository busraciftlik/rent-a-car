package com.busraciftlik.repository.abstracts;


import com.busraciftlik.entities.concretes.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandRepository {
    List<Brand> getAll();
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    Brand getById(int id);
    void delete(int id);


}
