package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.repository.OrderRepository;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;

/**
 * Created by root on 11/03/17.
 */
public class OrderService {

    // Get Orders from local Database
    public static List<SalesOrder> findAll(Context context) {

        OrderRepository db = new OrderRepository(context);

        List<SalesOrder> salesOrders = db.findAll();

        return salesOrders;
    }

    // Get SalesOrder from local Database
    public static SalesOrder findById(Context context, long id) {

        OrderRepository db = new OrderRepository(context);
        SalesOrder salesOrder = db.findById(id);
        return salesOrder;
    }

}
