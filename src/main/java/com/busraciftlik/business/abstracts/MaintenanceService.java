package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateMaintenanceRequest;
import com.busraciftlik.business.dto.requests.update.UpdateMaintenanceRequest;
import com.busraciftlik.business.dto.responses.create.CreateMaintenanceResponse;
import com.busraciftlik.business.dto.responses.get.GetAllMaintenancesResponse;
import com.busraciftlik.business.dto.responses.get.GetMaintenanceResponse;
import com.busraciftlik.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int id);
    GetMaintenanceResponse returnCarFromMaintenance(int carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    void delete(int id);
}
