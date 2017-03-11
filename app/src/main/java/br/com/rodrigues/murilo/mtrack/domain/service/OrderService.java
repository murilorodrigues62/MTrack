package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.repository.OrderRepository;
import br.com.rodrigues.murilo.mtrack.domain.model.Order;

/**
 * Created by root on 11/03/17.
 */
public class OrderService {

    // Get Orders from local Database
    public static List<Order> findAll(Context context) {

        OrderRepository db = new OrderRepository(context);

        List<Order> orders = db.findAll();

        return orders;
    }

    // Get Order from local Database
    public static Order findById(Context context, long id) {

        OrderRepository db = new OrderRepository(context);
        Order order = db.findById(id);
        return order;
    }

}
