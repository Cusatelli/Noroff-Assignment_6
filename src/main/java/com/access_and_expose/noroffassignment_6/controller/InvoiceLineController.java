package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.InvoiceLineService;
import com.access_and_expose.noroffassignment_6.data.service.InvoiceService;
import com.access_and_expose.noroffassignment_6.model.InvoiceLine;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name = "InvoiceLine")
@RequestMapping(value = "api/v1/invoice-line")
public class InvoiceLineController {

    private final InvoiceLineService invoiceLineService;

    /**
     * Constructor injected with services.
     * @param invoiceLineService InvoiceLine Invoice.
     */
    public InvoiceLineController(InvoiceLineService invoiceLineService) {
        this.invoiceLineService = invoiceLineService;
    }

    /**
     * Get all InvoiceLine objects from SQL Database Chinook using SQL Queries.
     * @param offset start getting all elements from offset value - default to 0
     * @param limit end getting all elements at offset + limit.
     * @return Collection of InvoiceLine model objects.
     */
    @GetMapping(value = "/")
    public Collection<InvoiceLine> getAllInvoiceLines(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.invoiceLineService.getAll(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.invoiceLineService.getAll();
        }
    }

}
