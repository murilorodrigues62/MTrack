package br.com.rodrigues.murilo.mtrack.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductCountAdapter extends RecyclerView.Adapter<ProductCountAdapter.MyHolder> {

    private final List<SalesOrder> products;

    public ProductCountAdapter(ArrayList products) {
        this.products = products;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_product_count, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        // TODO: 18/03/17
        //holder.title.setText(products.get(position).getProductCode() + " - " + products.get(position).getProductName());
       // holder.count.setText(String.valueOf(products.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    /**
     * Método publico chamado para atualziar a lista.
     *
     * @param product
     */
    public void updateList(SalesOrder product) {
        insertItem(product);
    }

    // Insert a new product
    private void insertItem(SalesOrder product) {
        products.add(product); // TODO: 13/02/17 ajustar
        notifyItemInserted(getItemCount());
    }

    // Update a product
    private void updateItem(int position) {
        SalesOrder product = products.get(position);
        //product.incrementRead(); // TODO: 10/02/17 Criar imcrementRead para incrementar quantidade lida do produto
        notifyItemChanged(position);
    }

    // Remove a product
    private void removerItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.product_name)
        public TextView title;

        @Bind(R.id.product_count)
        public TextView count;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}