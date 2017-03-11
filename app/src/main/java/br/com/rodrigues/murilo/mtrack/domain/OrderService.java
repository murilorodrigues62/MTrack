package br.com.rodrigues.murilo.mtrack.domain;

import android.content.Context;

import java.util.List;

/**
 * Created by root on 11/03/17.
 */
public class OrderService {

    // Get Orders from local Database
    public static List<Order> getOrders(Context context) {

        OrderDB db = new OrderDB(context);

        List<Order> orders = db.findAll();

        return orders;
    }

}
