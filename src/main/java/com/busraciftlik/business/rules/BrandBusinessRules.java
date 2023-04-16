package com.busraciftlik.business.rules;

import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import com.busraciftlik.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Brand.notExists);
        }
    }

    public void checkIfBrandExistsByName(String name){
        if(repository.existsByNameIgnoreCase(name)){
            throw new BusinessException(Messages.Brand.exists);
        }
    }
}
