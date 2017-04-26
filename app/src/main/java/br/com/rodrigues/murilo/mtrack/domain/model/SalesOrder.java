package br.com.rodrigues.murilo.mtrack.domain.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.infra.service.ProductService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderItemService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderPackageService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;

public class SalesOrder {
    @SerializedName("Id")
    private int idSalesOrder;
    @SerializedName("IdDelivery")
    private int idDelivery;
    @SerializedName("Customer")
    private Customer customer = null;
    @SerializedName("Delivered")
    private boolean delivered;
    @SerializedName("SalesOrderItems")
    private List<SalesOrderItem> salesOrderItems = null;

    public SalesOrder() { }

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

    public List<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
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

    public ReturnMessage readPackage(Context context, String barcode){

        // not read if order was finished
        if (this.isDelivered()) {
            return new ReturnMessage(false, context.getString(R.string.sales_order_was_finished));
        }

        // Search for package barcode
        SalesOrderPackage salesOrderPackage = SalesOrderPackageService.findByBarcode(context, this.getIdDelivery(), barcode);
        if (salesOrderPackage == null){
            return new ReturnMessage(false, context.getString(R.string.msg_package_not_found));
        } else {
            
            if (salesOrderItems == null) {
                salesOrderItems = SalesOrderItemService.findByIdOrder(context, this.getIdSalesOrder());
            }

            // Search for product in items
            Boolean productInOrder = false;
            for (SalesOrderItem salesOrderItem: salesOrderItems) {

                if (salesOrderItem.getProduct().equals(ProductService.findById(context, salesOrderPackage.getIdProduct()))){
                    productInOrder = true;

                    // Check total packages read
                    if (salesOrderItem.getQuantityRead(context) >= salesOrderItem.getQuantity()){
                        return new ReturnMessage(false, context.getString(R.string.msg_packages_read));
                    }

                    if (salesOrderPackage.getIdSalesOrderReal() > 0){
                        return new ReturnMessage(false, context.getString(R.string.msg_package_read) + salesOrderPackage.getIdSalesOrderReal());
                    } else {
                        salesOrderPackage.setIdSalesOrderReal(this.getIdSalesOrder());
                    }

                    // Update Sales Order Real of package
                    if (SalesOrderPackageService.updateSalesOrderReal(context, salesOrderPackage)) {
                        return new ReturnMessage(true, context.getString(R.string.msg_package_read_ok));
                    }
                }
            }

            if (!productInOrder){
                return new ReturnMessage(false, context.getString(R.string.msg_product_not_found));
            }
        }
        return new ReturnMessage(false, context.getString(R.string.msg_package_not_found));
    }

    public ReturnMessage removePackage(Context context, String barcode) {
        // not read if order was finished
        if (this.isDelivered()) {
            return new ReturnMessage(false, context.getString(R.string.sales_order_was_finished));
        }

        // Search for package in this order
        SalesOrderPackage salesOrderPackage = SalesOrderPackageService.findInOrder(context, this, barcode);
        if (salesOrderPackage == null){
            return new ReturnMessage(false, context.getString(R.string.msg_package_not_found));
        } else {

            // Update Sales Order Real of package
            if (SalesOrderPackageService.removeSalesOrderReal(context, salesOrderPackage)) {
                return new ReturnMessage(true, context.getString(R.string.package_removed));
            }
        }
        return new ReturnMessage(false, context.getString(R.string.msg_package_not_found));
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
