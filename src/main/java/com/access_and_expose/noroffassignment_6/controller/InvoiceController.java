package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.InvoiceService;
import com.access_and_expose.noroffassignment_6.model.Invoice;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name = "Invoice")
@RequestMapping(value = "api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * Constructor injected with services.
     * @param invoiceService Genre Invoice.
     */
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Get all Invoice objects from SQL Database Chinook using SQL Queries.
     * @param offset start getting all elements from offset value - default to 0
     * @param limit end getting all elements at offset + limit.
     * @return Collection of Invoice model objects.
     */
    @GetMapping(value = "/")
    public Collection<Invoice> getAllInvoices(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.invoiceService.getAll(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.invoiceService.getAll();
        }
    }
}
