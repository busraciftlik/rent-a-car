package com.busraciftlik.business.dto.responses.create;

import com.busraciftlik.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private State state;
    private int modelId;
}
