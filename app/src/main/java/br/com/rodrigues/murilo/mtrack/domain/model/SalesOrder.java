package br.com.rodrigues.murilo.mtrack.domain.model;

public class SalesOrder {
    private long idSalesOrder;
    private String idDelivery;
    private long idCustomer;
    private String customerName;
    private long idProduct;
    private String productCode;
    private String productName;
    private long quantity;
    private boolean delivered;

    public SalesOrder() {

    }

    public SalesOrder(long idSalesOrder, String IdDelivery, String customerName) {
        this.idSalesOrder = idSalesOrder;
        this.idDelivery = IdDelivery;
        this.customerName = customerName;
    }

    public SalesOrder(long idSalesOrder, String idDelivery, long idCustomer, String customerName, long idProduct, String productCode, String productName, long quantity, boolean delivered) {
        this.idSalesOrder = idSalesOrder;
        this.idDelivery = idDelivery;
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.idProduct = idProduct;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "Order " + idSalesOrder;
    }

    public long getIdSalesOrder() {
        return idSalesOrder;
    }

    public void setIdSalesOrder(long idSalesOrder) {
        this.idSalesOrder = idSalesOrder;
    }

    public String getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(String idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
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
