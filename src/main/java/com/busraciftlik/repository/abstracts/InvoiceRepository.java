package com.busraciftlik.repository.abstracts;

import com.busraciftlik.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
