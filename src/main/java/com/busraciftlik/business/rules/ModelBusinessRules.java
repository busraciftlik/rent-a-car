package com.busraciftlik.business.rules;

import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import com.busraciftlik.repository.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    public final ModelRepository repository;

    public void checkIfModelExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Model.notExists);
        }
    }
}
