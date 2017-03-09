package br.com.rodrigues.murilo.mtrack.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rodrigues.murilo.mtrack.model.Product;

/**
 * Created by root on 08/03/17.
 */
public class DummyProduct {

    /**
     * An array of sample items.
     */
    public static final List<Product> ITEMS = new ArrayList<>();

    /**
     * A map of sample items. Key: sample ID; Value: Item.
     */
    public static final Map<Integer, Product> ITEM_MAP = new HashMap<>(5);

    static {
        addItem(new Product(1, "Product 1"));
        addItem(new Product(2, "Product 2"));
        addItem(new Product(3, "Product 3"));
        addItem(new Product(4, "Product 4"));
        addItem(new Product(5, "Product 5"));
        addItem(new Product(6, "Product 6"));
        addItem(new Product(7, "Product 7"));
        addItem(new Product(8, "Product 8"));
        addItem(new Product(9, "Product 9"));
        addItem(new Product(10, "Product 10"));
    }

    private static void addItem(Product item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
