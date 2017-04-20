package br.com.rodrigues.murilo.mtrack.infra.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;
import br.com.rodrigues.murilo.mtrack.domain.repository.ProductRepository;

public class ProductService {

    // Get Orders from local Database
    public static List<Product> findAll(Context context) {

        ProductRepository db = new ProductRepository(context);

        List<Product> products = db.findAll();

        return products;
    }

    // Get Product from local Database
    public static Product findById(Context context, int idProduct) {

        ProductRepository db = new ProductRepository(context);
        Product product = db.findById(idProduct);
        return product;
    }

    public static boolean insert(Context context, Product product){
        ProductRepository db = new ProductRepository(context);
        return db.insert(product);
    }

    public static boolean deleteAll(Context context){
        ProductRepository db = new ProductRepository(context);
        return db.deleteAll();
    }

}
