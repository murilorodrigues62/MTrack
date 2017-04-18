package br.com.rodrigues.murilo.mtrack.infra.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderItemRepository;

public class SalesOrderItemService {

    // Get Orders from local Database
    public static List<SalesOrderItem> findAll(Context context) {

        SalesOrderItemRepository db = new SalesOrderItemRepository(context);
        List<SalesOrderItem> salesOrderItems = db.findAll();
        return salesOrderItems;
    }

    // Get Orders from local Database
    public static List<SalesOrderItem> findByIdOrder(Context context, int idSalesOrder) {

        SalesOrderItemRepository db = new SalesOrderItemRepository(context);
        List<SalesOrderItem> salesOrderItems = db.findByOrder(idSalesOrder);
        return salesOrderItems;
    }

    // Get Items from local Database
    public static SalesOrderItem findById(Context context, int idSalesOrderItem) {

        SalesOrderItemRepository db = new SalesOrderItemRepository(context);
        SalesOrderItem item = db.findById(idSalesOrderItem);
        return item;
    }

    public static boolean insert(Context context, SalesOrderItem salesOrderItem){
        SalesOrderItemRepository db = new SalesOrderItemRepository(context);
        return db.insert(salesOrderItem);
    }
}
