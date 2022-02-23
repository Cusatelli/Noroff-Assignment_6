package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.repository.album.IAlbumRepository;
import com.access_and_expose.noroffassignment_6.data.repository.invoice.IInvoiceRepository;
import com.access_and_expose.noroffassignment_6.model.Invoice;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashMap;

@RestController
@Tag(name = "Invoice")
@RequestMapping(value = "api/v1/invoice")
public class InvoiceController {

    private final IInvoiceRepository invoiceRepository;

    public InvoiceController(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping(value = "/")
    public Collection<Invoice> getAllInvoices(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.invoiceRepository.getAll(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.invoiceRepository.getAll();
        }
    }

    @GetMapping(value = "/highestSpender")
    public LinkedHashMap<Long, Long> getInvoiceByHighestSpender() {
        return this.invoiceRepository.getByHighestSpender();
    }
}
