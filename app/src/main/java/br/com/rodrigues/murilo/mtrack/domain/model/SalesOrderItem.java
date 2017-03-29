package br.com.rodrigues.murilo.mtrack.domain.model;

import android.content.Context;

import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderPackageService;

public class SalesOrderItem {
    private int idSalesOrderItem;
    private SalesOrder salesOrder;
    private Product product;
    private int quantity;
    private int quantityRead;

    public SalesOrderItem() {}

    public int getIdSalesOrderItem() {
        return idSalesOrderItem;
    }

    public void setIdSalesOrderItem(int idSalesOrderItem) {
        this.idSalesOrderItem = idSalesOrderItem;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityRead(Context context) {
        quantityRead = SalesOrderPackageService.findBySalesOrderReal(context, this).size();
        return quantityRead;
    }

    @Override
    public String toString() {
        return product.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrderItem that = (SalesOrderItem) o;

        return idSalesOrderItem == that.idSalesOrderItem;
    }

    @Override
    public int hashCode() {
        return idSalesOrderItem;
    }
}