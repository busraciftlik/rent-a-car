package com.busraciftlik.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    private String cardHolder;
    private String modelName;
    private String brandName;
    private String plate;
    private int modelYear;
    private double dailyPrice;
    private int rentedOfDays;
    private LocalDateTime rentedAt;

}