package com.access_and_expose.noroffassignment_6.data.repository.invoice;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

@Repository
public interface IInvoiceRepository extends RestRepository<Invoice> {
    LinkedHashMap<Long, Long> getByHighestSpender();
}
