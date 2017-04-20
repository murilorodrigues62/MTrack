package br.com.rodrigues.murilo.mtrack.infra;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderPackage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/product")
    Call<List<Product>> getProducts();

    @GET("api/transporter/{id}/order")
    Call<List<SalesOrder>> getTransporterSalesOrder(@Path("id") String id);

    @GET("api/transporter/{id}/package")
    Call<List<SalesOrderPackage>> getSalesOrderPackage(@Path("id") String id);

    @PUT("api/package/")
    Call<List<SalesOrderPackage>> putSalesOrderPackage(@Body List<SalesOrderPackage> salesOrderPackages);
}
