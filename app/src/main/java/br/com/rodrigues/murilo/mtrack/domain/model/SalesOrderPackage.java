package br.com.rodrigues.murilo.mtrack.domain.model;

public class SalesOrderPackage {
    private int idDelivery;
    private SalesOrder salesOrder;
    private Product product;
    private SalesOrder salesOrderReal;
    private String barcode;

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


}
