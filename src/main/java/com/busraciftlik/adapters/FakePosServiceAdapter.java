package com.busraciftlik.adapters;

import com.busraciftlik.business.abstracts.PosService;
import com.busraciftlik.common.constants.Messages;
import com.busraciftlik.core.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) {
            throw new BusinessException(Messages.Payment.failed);
        }
    }
}
