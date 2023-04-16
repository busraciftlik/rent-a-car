package com.busraciftlik.business.abstracts;

import com.busraciftlik.business.dto.requests.create.CreateInvoiceRequest;
import com.busraciftlik.business.dto.requests.update.UpdateInvoiceRequest;
import com.busraciftlik.business.dto.responses.create.CreateInvoiceResponse;
import com.busraciftlik.business.dto.responses.get.GetAllInvoicesResponse;
import com.busraciftlik.business.dto.responses.get.GetInvoiceResponse;
import com.busraciftlik.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(int id);

    CreateInvoiceResponse add(CreateInvoiceRequest request);

    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);

    void delete(int id);
}
