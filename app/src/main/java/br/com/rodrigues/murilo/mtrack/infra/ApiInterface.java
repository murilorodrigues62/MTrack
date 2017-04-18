package br.com.rodrigues.murilo.mtrack.infra;

import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/product")
    Call<List<Product>> getProducts();

    @GET("api/transporter/{id}/order")
    Call<List<SalesOrder>> getTransporteSalesOrder(@Path("id") String id);
}
