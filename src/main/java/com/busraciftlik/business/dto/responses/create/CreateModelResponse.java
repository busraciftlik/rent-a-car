package com.busraciftlik.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelResponse {
    private int id;
    private String name;
    private int brandId;

}
