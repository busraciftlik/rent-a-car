package com.busraciftlik.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceRequest {
    private Date startDate;
    private Date dueDate;
    private double maintenanceCost;
    private String description;
    private int carId;
}
