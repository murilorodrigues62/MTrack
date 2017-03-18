package br.com.rodrigues.murilo.mtrack.domain.model;

public class SalesOrder {
    private int idSalesOrder;
    private int idDelivery;
    private Customer customer = null;
    private int idProduct;
    private boolean delivered;

    public SalesOrder() {

    }

    @Override
    public String toString() {
        return "Order " + idSalesOrder;
    }

    public int getIdSalesOrder() {
        return idSalesOrder;
    }

    public void setIdSalesOrder(int idSalesOrder) {
        this.idSalesOrder = idSalesOrder;
    }

    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrder salesOrder = (SalesOrder) o;

        return idSalesOrder == salesOrder.idSalesOrder;
    }

    @Override
    public int hashCode() {
        return (int) (idSalesOrder ^ (idSalesOrder >>> 32));
    }
}
