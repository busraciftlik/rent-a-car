package com.busraciftlik.business.dto.requests.create;

import com.busraciftlik.common.constants.Regex;
import com.busraciftlik.common.utils.annotations.NotFutureYear;
import com.busraciftlik.entities.enums.State;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int modelId;
    @Min(1998)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.PLATE )
    private String plate;
    @Min(1)
    private double dailyPrice;
    private State state;

}
