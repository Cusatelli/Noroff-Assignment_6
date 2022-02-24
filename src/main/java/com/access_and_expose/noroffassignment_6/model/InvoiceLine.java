package com.access_and_expose.noroffassignment_6.model;

public class InvoiceLine {
    private Long id;
    private Long trackId;
    private Long invoiceId;
    private Long unitPrice;
    private Long quantity;

    public InvoiceLine(Long id, Long invoiceId, Long trackId, Long unitPrice, Long quantity) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.trackId = trackId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTrackId() {
        return trackId;
    }
    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }
    public Long getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    public Long getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", trackId=" + trackId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
