package br.com.rodrigues.murilo.mtrack.infra.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Customer;
import br.com.rodrigues.murilo.mtrack.domain.repository.CustomerRepository;

/**
 * Created by root on 11/03/17.
 */
public class CustomerService {

    // Get Orders from local Database
    public static List<Customer> findAll(Context context) {

        CustomerRepository db = new CustomerRepository(context);

        List<Customer> customers = db.findAll();

        return customers;
    }

    // Get Customer from local Database
    public static Customer findById(Context context, int idCustomer) {

        CustomerRepository db = new CustomerRepository(context);
        Customer customer = db.findById(idCustomer);
        return customer;
    }

    public static boolean insert(Context context, Customer customer){
        CustomerRepository db = new CustomerRepository(context);
        return db.insert(customer);
    }
    public static boolean deleteAll(Context context){
        CustomerRepository db = new CustomerRepository(context);
        return db.deleteAll();
    }
}
