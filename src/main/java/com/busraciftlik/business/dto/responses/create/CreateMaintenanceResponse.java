package com.busraciftlik.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceResponse {
    private int id;
    private int carId;
    private boolean isCompleted;
    private String information;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
}
