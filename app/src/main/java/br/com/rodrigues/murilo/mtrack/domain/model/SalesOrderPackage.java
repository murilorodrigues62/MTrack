package br.com.rodrigues.murilo.mtrack.domain.model;

import com.google.gson.annotations.SerializedName;

public class SalesOrderPackage {
    @SerializedName("IdDelivery")
    private int idDelivery;
    private SalesOrder salesOrder;
    private Product product;
    private SalesOrder salesOrderReal;
    @SerializedName("Barcode")
    private String barcode;

    // Private attribute just used to get data in Json and insert into database
    @SerializedName("IdSalesOrder")
    private int idSalesOrder;
    @SerializedName("IdSalesOrderReal")
    private int idSalesOrderReal;
    @SerializedName("IdProduct")
    private int idProduct;

    public SalesOrderPackage() {}

    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SalesOrder getSalesOrderReal() {
        return salesOrderReal;
    }

    public void setSalesOrderReal(SalesOrder salesOrderReal) {
        this.salesOrderReal = salesOrderReal;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getIdSalesOrder() {
        return idSalesOrder;
    }

    public void setIdSalesOrder(int idSalesOrder) {
        this.idSalesOrder = idSalesOrder;
    }

    public int getIdSalesOrderReal() {
        return idSalesOrderReal;
    }

    public void setIdSalesOrderReal(int idSalesOrderReal) {
        this.idSalesOrderReal = idSalesOrderReal;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
