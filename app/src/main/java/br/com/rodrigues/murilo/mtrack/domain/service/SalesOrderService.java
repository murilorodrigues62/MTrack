package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderRepository;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;

public class SalesOrderService {

    // Get Orders from local Database
    public static List<SalesOrder> findAll(Context context) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        List<SalesOrder> salesOrders = db.findAll();
        return salesOrders;
    }

    // Get Orders from local Database
    public static SalesOrder findById(Context context, int idSalesOrder) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        SalesOrder salesOrder = db.findById(idSalesOrder);
        return salesOrder;
    }

    public static boolean finishOrder(Context context, SalesOrder salesOrder) {
        SalesOrderRepository db = new SalesOrderRepository(context);
        return db.finishOrder(salesOrder.getIdSalesOrder(), salesOrder.isDelivered());
    }
}
