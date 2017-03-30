package br.com.rodrigues.murilo.mtrack.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SalesOrderItemAdapter extends RecyclerView.Adapter<SalesOrderItemAdapter.MyHolder> {
    private ViewGroup parent;
    private final List<SalesOrderItem> salesOrderItems;

    public SalesOrderItemAdapter(ArrayList salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;

        return new MyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sales_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(salesOrderItems.get(position).getProduct().toString());
        holder.count.setText(String.valueOf(salesOrderItems.get(position).getQuantity()));
        holder.read.setText(String.valueOf(salesOrderItems.get(position).getQuantityRead(parent.getContext())));

    }

    @Override
    public int getItemCount() {
        return salesOrderItems != null ? salesOrderItems.size() : 0;
    }

    // Update a product
    private void updateItem(int position) {
        SalesOrderItem salesOrderItem = salesOrderItems.get(position);
        notifyItemChanged(position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.product_name)
        public TextView title;

        @Bind(R.id.product_count)
        public TextView count;

        @Bind(R.id.product_read)
        public TextView read;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}