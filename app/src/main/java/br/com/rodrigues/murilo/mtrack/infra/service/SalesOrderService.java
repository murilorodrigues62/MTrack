package br.com.rodrigues.murilo.mtrack.infra.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderRepository;
import br.com.rodrigues.murilo.mtrack.infra.ApiClient;
import br.com.rodrigues.murilo.mtrack.infra.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesOrderService {

    // Get Orders from local Database
    public static List<SalesOrder> findAll(Context context) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        List<SalesOrder> salesOrders = db.findAll();
        return salesOrders;
    }

    // Get Orders from local Database
    public static SalesOrder findById(Context context, int idSalesOrder) {

        SalesOrderRepository db = new SalesOrderRepository(context);
        SalesOrder salesOrder = db.findById(idSalesOrder);
        return salesOrder;
    }

    public static boolean finishOrder(Context context, SalesOrder salesOrder) {
        SalesOrderRepository db = new SalesOrderRepository(context);
        return db.finishOrder(salesOrder.getIdSalesOrder(), salesOrder.isDelivered());
    }

    // Get all Transporter Sales Order from Web Service
    public static boolean syncSalesOrder(final Context context) {
        // TODO: 17/04/17 Create Thread
        String url = SettingsService.findSettings(context).getUrlEdata();
        String transporter = SettingsService.findSettings(context).getTransporterCode();

        ApiInterface apiInterface = ApiClient.getClient(url).create(ApiInterface.class);

        Call<List<SalesOrder>> call = apiInterface.getTransporteSalesOrder(transporter);

        call.enqueue(new Callback<List<SalesOrder>>() {
            @Override
            public void onResponse(Call<List<SalesOrder>> call, Response<List<SalesOrder>> response) {

                // Get data from JSON to Objects
                List<SalesOrder> salesOrders = response.body();
                // Persists Object in DB
                sync(context, salesOrders);
                // TODO: 17/04/17 Sync packages and delivery

            }

            @Override
            public void onFailure(Call<List<SalesOrder>> call, Throwable t) {
                call.cancel();
            }
        });

        return true;
    }

    public static boolean insert(Context context, SalesOrder salesOrder){
        SalesOrderRepository db = new SalesOrderRepository(context);
        return db.insert(salesOrder);
    }

    private static boolean sync(Context context, List<SalesOrder> salesOrders){
        for (SalesOrder salesOrder: salesOrders) {

            // Insert Sales Order in DB if not exists
            if (SalesOrderService.findById(context, salesOrder.getIdSalesOrder()) == null){
                insert(context, salesOrder);
            }

            // Insert Customer in DB if not exists
            if(CustomerService.findById(context, salesOrder.getCustomer().getIdCustomer()) == null){
                CustomerService.insert(context, salesOrder.getCustomer());
            }

            for (SalesOrderItem item: salesOrder.getSalesOrderItems()) {

                // Insert Item in DB if not exists
                if(SalesOrderItemService.findById(context, item.getIdSalesOrderItem()) == null){
                    item.setSalesOrder(salesOrder);
                    SalesOrderItemService.insert(context, item);
                }

                // Insert Product in DB if not exists
                if(ProductService.findById(context, item.getProduct().getIdProduct()) == null){
                    ProductService.insert(context, item.getProduct());
                }

            }
        }

        return true;
    }
}
