package com.busraciftlik.business.dto.requests.create;

import com.busraciftlik.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private double dailyPrice;
    private int rentedForDays;
    private PaymentRequest paymentRequest;
}
