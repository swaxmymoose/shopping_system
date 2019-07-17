/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author rofth173
 */
public class Sale {
    private String saleId;
    private LocalDate date;
    private String status;
    private Customer customer;
    private ArrayList<SaleItem> items;
    
    public BigDecimal getTotal() {
        throw new UnsupportedOperationException();
    }
    
    public void addItem(SaleItem item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Sale{" + "saleId=" + saleId + ", date=" + date + ", status=" + status + ", customer=" + customer + ", items=" + items + '}';
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<SaleItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SaleItem> items) {
        this.items = items;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
