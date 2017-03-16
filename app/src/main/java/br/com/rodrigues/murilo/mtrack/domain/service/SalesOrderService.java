package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderRepository;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;

/**
 * Created by root on 11/03/17.
 */
public class SalesOrderService {

    // Get Orders from local Database
    public static List<SalesOrder> findAll(Context context) {

        SalesOrderRepository db = new SalesOrderRepository(context);

        List<SalesOrder> salesOrders = db.findAll();

        return salesOrders;
    }

    // Get SalesOrder from local Database
    public static SalesOrder findSalesOrder(Context context, long idSalesOrder) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        SalesOrder salesOrder = db.findSalesOrder(idSalesOrder);
        return salesOrder;
    }

    // Get SalesOrder from local Database
    public static List<SalesOrder> findProducts(Context context, long idSalesOrder) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        List<SalesOrder> salesOrders = db.findByProducts(idSalesOrder);
        return salesOrders;
    }

    // Get Orders from local Database
    public static List<SalesOrder> findAllGrouped(Context context) {

        SalesOrderRepository db = new SalesOrderRepository(context);

        List<SalesOrder> salesOrders = db.findAllGrouped();

        return salesOrders;
    }

}
