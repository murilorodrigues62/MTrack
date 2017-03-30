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

public class SalesOrderAdapter extends RecyclerView.Adapter<SalesOrderAdapter.MyHolder> {
    private final List<SalesOrder> salesOrders;
    private OrderOnClickListener orderOnClickListener;
    private ViewGroup parent;

    public SalesOrderAdapter(ArrayList orders, OrderOnClickListener orderOnClickListener) {
        this.salesOrders = orders;
        this.orderOnClickListener = orderOnClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        return new MyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sales_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.textOrder.setText(parent.getContext().getText(R.string.order) + " " + String.valueOf(salesOrders.get(position).getIdSalesOrder()));
        holder.textCustomer.setText(salesOrders.get(position).getCustomer().getCustomerName());
        //holder.textStatus.setText(salesOrders.get(position).isDelivered()? "Delivered": "Not Delivered");
        // TODO: 29/03/17 [Whish] - Show status and refresh when it's update using starActivityForResult

        // On Click
        if (orderOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A variável position é final
                    orderOnClickListener.onClickOrder(holder.itemView, salesOrders.get(position).getIdSalesOrder());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return salesOrders != null ? salesOrders.size() : 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.order_list_order)
        public TextView textOrder;

        @Bind(R.id.order_list_customer)
        public TextView textCustomer;

        //@Bind(R.id.order_list_status)
        //public TextView textStatus;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OrderOnClickListener {
       void onClickOrder(View view, int id);
        // TODO: 11/03/17 change id for SalesOrder serealizable
    }
}