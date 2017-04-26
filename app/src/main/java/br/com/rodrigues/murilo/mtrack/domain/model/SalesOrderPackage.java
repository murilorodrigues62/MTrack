package br.com.rodrigues.murilo.mtrack.domain.model;

import com.google.gson.annotations.SerializedName;

public class SalesOrderPackage {
    @SerializedName("IdDelivery")
    private int idDelivery;
    @SerializedName("Barcode")
    private String barcode;
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
