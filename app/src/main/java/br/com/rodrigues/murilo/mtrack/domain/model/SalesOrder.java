package br.com.rodrigues.murilo.mtrack.domain.model;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderItemService;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderPackageService;

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

    public String readPackage(Context context, String barcode){

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
                    if (SalesOrderPackageService.findBySalesOrderReal(context, this.getIdSalesOrder()).size() >=
                            salesOrderItem.getQuantity()){
                        return context.getString(R.string.msg_packages_read);
                    }

                    if (salesOrderPackage.getSalesOrderReal() != null){
                        return context.getString(R.string.msg_package_read) + salesOrderPackage.getSalesOrderReal().getIdSalesOrder();
                    } else {
                        salesOrderPackage.setSalesOrderReal(this);
                    }

                    // Update Sales Order Real of package
                    SalesOrderPackageService.updateSalesOrderReal(context, salesOrderPackage);

                    // TODO: 28/03/17   inc qtde read on the list

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
