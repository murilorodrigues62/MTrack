package br.com.rodrigues.murilo.mtrack.domain.model;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderItemService;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderPackageService;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderService;

public class SalesOrder {
    private int idSalesOrder;
    private int idDelivery;
    private Customer customer = null;
    private int idProduct;
    private boolean delivered;
    private List<SalesOrderItem> salesOrderItems = null;

    public SalesOrder() { }

    @Override
    public String toString() {
        return "Order " + idSalesOrder; // TODO: 28/03/17 change this text
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

    public String finishOrder(Context context){
        // not update if order was finished
        if (this.isDelivered()) {
            return context.getString(R.string.sales_order_was_finished);
        }

        this.setDelivered(true);

        return (SalesOrderService.finishOrder(context, this)) ? context.getString(R.string.sales_order_finished) : context.getString(R.string.sales_order_not_finished);
    }

    public String OpenOrder(Context context) {
        // not update if order was finished
        if (!this.isDelivered()) {
            return context.getString(R.string.sales_order_was_opened);
        }
        
        this.setDelivered(false);

        return (SalesOrderService.finishOrder(context, this)) ? context.getString(R.string.sales_order_opened) : context.getString(R.string.sales_order_not_opened);

    }

    public String readPackage(Context context, String barcode){

        // not read if order was finished
        if (this.isDelivered()) {
            return context.getString(R.string.sales_order_was_finished);
        }

        // Search for package barcode
        SalesOrderPackage salesOrderPackage = SalesOrderPackageService.findByBarcode(context, this.getIdDelivery(), barcode);
        if (salesOrderPackage == null){
            return context.getString(R.string.msg_package_not_found);
        } else {
            
            if (salesOrderItems == null) {
                salesOrderItems = SalesOrderItemService.findByIdOrder(context, this.getIdSalesOrder());
            }

            // Search for product in items
            Boolean productInOrder = false;
            for (SalesOrderItem salesOrderItem: salesOrderItems) {

                if (salesOrderItem.getProduct().equals(salesOrderPackage.getProduct())){
                    productInOrder = true;

                    // Check total packages read
                    if (salesOrderItem.getQuantityRead(context) >= salesOrderItem.getQuantity()){
                        return context.getString(R.string.msg_packages_read);
                    }

                    if (salesOrderPackage.getSalesOrderReal() != null){
                        return context.getString(R.string.msg_package_read) + salesOrderPackage.getSalesOrderReal().getIdSalesOrder();
                    } else {
                        salesOrderPackage.setSalesOrderReal(this);
                    }

                    // Update Sales Order Real of package
                    SalesOrderPackageService.updateSalesOrderReal(context, salesOrderPackage);

                    return context.getString(R.string.msg_package_read_ok);
                }
            }

            if (!productInOrder){
                return context.getString(R.string.msg_product_not_found);
            }
        }
        return context.getString(R.string.msg_package_not_found);
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
