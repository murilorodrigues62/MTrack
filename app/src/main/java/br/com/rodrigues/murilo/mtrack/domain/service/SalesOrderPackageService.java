package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderPackage;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderPackageRepository;

public class SalesOrderPackageService {

    // Get Orders from local Database
    public static List<SalesOrderPackage> findAll(Context context) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findAll();
        return salesOrderPackages;
    }

    public static List<SalesOrderPackage> findBySalesOrder(Context context, int idSalesOrder) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findBySalesOrder(idSalesOrder);
        return salesOrderPackages;
    }

    public static List<SalesOrderPackage> findBySalesOrderReal(Context context, SalesOrderItem salesOrderItem) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findBySalesOrderReal(salesOrderItem.getSalesOrder().getIdSalesOrder(), salesOrderItem.getProduct().getIdProduct());
        return salesOrderPackages;
    }

    public static SalesOrderPackage findByBarcode(Context context, int idDelivery,  String barcode) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        SalesOrderPackage salesOrderPackage = db.findByBarCode(idDelivery, barcode);
        return salesOrderPackage;
    }

    public static int updateSalesOrderReal(Context context, SalesOrderPackage salesOrderPackage){
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        return db.updateSalesOrderReal(salesOrderPackage);
    }
}
