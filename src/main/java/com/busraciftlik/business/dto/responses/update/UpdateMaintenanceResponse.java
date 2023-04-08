package com.busraciftlik.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceResponse {
    private int id;
    private int carId;
    private boolean isCompleted;
    private String information;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
}
