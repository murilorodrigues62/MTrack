package br.com.rodrigues.murilo.mtrack.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rodrigues.murilo.mtrack.model.Order;

public class DummyOrder {

    /**
     * An array of sample items.
     */
    public static final List<Order> ITEMS = new ArrayList<>();

    /**
     * A map of sample items. Key: sample ID; Value: Item.
     */
    public static final Map<String, Order> ITEM_MAP = new HashMap<>(5);

    static {
        addItem(new Order("1", "Order #1", "Client 1"));
        addItem(new Order("2", "Order #2", "Client 2"));
        addItem(new Order("3", "Order #3", "Client 3"));
        addItem(new Order("4", "Order #4", "Client 4"));
        addItem(new Order("5", "Order #5", "Client 5"));
    }

    private static void addItem(Order item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
