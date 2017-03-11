package br.com.rodrigues.murilo.mtrack.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;

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
        addItem(new Product(1, "001", "Product 1"));
        addItem(new Product(2, "002", "Product 2"));
    }

    private static void addItem(Product item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
