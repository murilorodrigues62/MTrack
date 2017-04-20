package br.com.rodrigues.murilo.mtrack.infra.service;

import android.content.Context;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderPackage;
import br.com.rodrigues.murilo.mtrack.domain.model.Settings;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderPackageRepository;
import br.com.rodrigues.murilo.mtrack.infra.ApiClient;
import br.com.rodrigues.murilo.mtrack.infra.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesOrderPackageService {

    // Get Orders from local Database
    public static List<SalesOrderPackage> findAll(Context context) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findAll();
        return salesOrderPackages;
    }

    public static List<SalesOrderPackage> findBySalesOrder(Context context, int idSalesOrder) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findBySalesOrder(idSalesOrder);
        return salesOrderPackages;
    }

    public static List<SalesOrderPackage> findBySalesOrderReal(Context context, SalesOrderItem salesOrderItem) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        List<SalesOrderPackage> salesOrderPackages = db.findBySalesOrderReal(salesOrderItem.getSalesOrder().getIdSalesOrder(), salesOrderItem.getProduct().getIdProduct());
        return salesOrderPackages;
    }

    public static SalesOrderPackage findByBarcode(Context context, int idDelivery,  String barcode) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        SalesOrderPackage salesOrderPackage = db.findByBarCode(idDelivery, barcode);
        return salesOrderPackage;
    }

    public static boolean updateSalesOrderReal(Context context, SalesOrderPackage salesOrderPackage){
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        return db.updateSalesOrderReal(salesOrderPackage) == 1;
    }

    public static SalesOrderPackage findInOrder(Context context, SalesOrder salesOrder, String barcode) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        SalesOrderPackage salesOrderPackage = db.findInOrder(salesOrder.getIdDelivery(), salesOrder.getIdSalesOrder(), barcode);
        return salesOrderPackage;
    }

    public static boolean removeSalesOrderReal(Context context, SalesOrderPackage salesOrderPackage) {
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        return db.removeSalesOrderReal(salesOrderPackage) == 1;
    }

    public static boolean insert(Context context, SalesOrderPackage salesOrderPackage){
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        return db.insert(salesOrderPackage);
    }

    // Get all Packages from Web Service
    public static boolean getSalesOrderPackageWS(final Context context, Settings settings) {

        ApiInterface apiInterface = ApiClient.getClient(settings.getUrlEdata()).create(ApiInterface.class);

        Call<List<SalesOrderPackage>> call = apiInterface.getSalesOrderPackage(settings.getTransporterCode());

        call.enqueue(new Callback<List<SalesOrderPackage>>() {
            @Override
            public void onResponse(Call<List<SalesOrderPackage>> call, Response<List<SalesOrderPackage>> response) {

                // Get data from JSON to Objects
                List<SalesOrderPackage> packages = response.body();
                // Persist Object in DB
                if (packages != null) sync(context, packages);
            }

            @Override
            public void onFailure(Call<List<SalesOrderPackage>> call, Throwable t) {
                call.cancel();
            }
        });

        return true;
    }

    private static boolean sync(Context context, List<SalesOrderPackage> salesOrderPackages){
        for (SalesOrderPackage salesOrderPackage: salesOrderPackages) {

            if (SalesOrderPackageService.findByBarcode(context, salesOrderPackage.getIdDelivery(), salesOrderPackage.getBarcode()) == null){
                SalesOrderPackageService.insert(context, salesOrderPackage);
            }
        }
        return true;
    }

    public static boolean putSalesOrderPackageWS(final Context context, Settings settings){

        ApiInterface apiInterface = ApiClient.getClient(settings.getUrlEdata()).create(ApiInterface.class);

        Call<List<SalesOrderPackage>> call = apiInterface.putSalesOrderPackage(SalesOrderPackageService.findAll(context));

        call.enqueue(new Callback<List<SalesOrderPackage>>() {
            @Override
            public void onResponse(Call<List<SalesOrderPackage>> call, Response<List<SalesOrderPackage>> response) {

                // TODO: 20/04/17  Verificar se será retornando lista de caixas no body, se não voltar, buscar todos os pedidos e pacotes fechados, chamar mesma função no sync
                clearDataBase(context);

            }

            @Override
            public void onFailure(Call<List<SalesOrderPackage>> call, Throwable t) {

            }
        });

        return true;
    }

    public static boolean clearDataBase(Context context){
        // TODO: 20/04/17 implementar a não exclusão de tudo mas só dos pedidos e ajustar parar atualizar o que já tiver no sqlite (Clientes e Produtos)
        SalesOrderPackageService.deleteAll(context);
        SalesOrderItemService.deleteAll(context);
        SalesOrderService.deleteAll(context);
        ProductService.deleteAll(context);
        CustomerService.deleteAll(context);

        return true;
    }

    // TODO: 20/04/17 Create Interface for Services
    public static boolean deleteAll(Context context){
        SalesOrderPackageRepository db = new SalesOrderPackageRepository(context);
        return db.deleteAll();
    }
}
