package com.access_and_expose.noroffassignment_6.model;

public class Invoice {
    private Long id;
    private Long customerId;
    private Long total;

    public Invoice(Long id, Long customerId, Long total) {
        this.id = id;
        this.customerId = customerId;
        this.total = total;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", total=" + total +
                '}';
    }
}
