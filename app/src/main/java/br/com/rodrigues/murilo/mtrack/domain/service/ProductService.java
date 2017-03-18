package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;
import br.com.rodrigues.murilo.mtrack.domain.repository.ProductRepository;

/**
 * Created by root on 11/03/17.
 */
public class ProductService {

    // Get Orders from local Database
    public static List<Product> findAll(Context context) {

        ProductRepository db = new ProductRepository(context);

        List<Product> products = db.findAll();

        return products;
    }

    // Get Customer from local Database
    public static Product findById(Context context, int idProduct) {

        ProductRepository db = new ProductRepository(context);
        Product product = db.findById(idProduct);
        return product;
    }

}
