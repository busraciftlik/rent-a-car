package com.busraciftlik.api.controllers;

import com.busraciftlik.business.abstracts.MaintenanceService;
import com.busraciftlik.business.dto.requests.create.CreateMaintenanceRequest;
import com.busraciftlik.business.dto.requests.update.UpdateMaintenanceRequest;
import com.busraciftlik.business.dto.responses.create.CreateMaintenanceResponse;
import com.busraciftlik.business.dto.responses.get.GetAllMaintenancesResponse;
import com.busraciftlik.business.dto.responses.get.GetMaintenanceResponse;
import com.busraciftlik.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenances")
@AllArgsConstructor
public class MaintenancesController {
    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id) {
        return maintenanceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request) {
        return maintenanceService.add(request);
    }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam int carId) {
        return maintenanceService.returnCarFromMaintenance(carId);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request/*,@RequestParam(defaultValue = "false") boolean isCompletedMaintenance*/) {
        return maintenanceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        maintenanceService.delete(id);
    }
}
