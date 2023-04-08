package com.busraciftlik.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
    private int carId;
    private boolean isCompleted;
    private String information;
    private LocalDate startDate;
    private LocalDate dueDate;
}