package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

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

    public static List<SalesOrderPackage> findBySalesOrderReal(Context context, int idSalesOrderReal) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findBySalesOrderReal(idSalesOrderReal);
        return salesOrderPackages;
    }

    public static SalesOrderPackage findByBarcode(Context context, String barcode) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        SalesOrderPackage salesOrderPackage = db.findByBarCode(barcode);
        return salesOrderPackage;
    }
}
