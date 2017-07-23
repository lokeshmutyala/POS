package com.example.android.effectivenavigation;

import java.io.Serializable;

/**
 * Created by lokeshmutyala on 17-06-2017.
 */

public class item implements Serializable {
    private String name;
    private Double price;
    private int quantity;
    private int no;
    private boolean isInvoice;
    private String barcode;
    private Double aDouble;
    private double tax;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public item(String name, Double price, int quantity, int no, boolean isInvoice, String barcode, Double aDouble,double tax) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.no = no;
        this.isInvoice=isInvoice;
        this.barcode=barcode;
        this.aDouble = aDouble;
        this.tax=tax;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public boolean isInvoice() {
        return isInvoice;
    }

    public void setInvoice(boolean invoice) {
        isInvoice = invoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


}