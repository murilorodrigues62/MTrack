package br.com.rodrigues.murilo.mtrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.model.Order;
import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder> {
    private final List<Order> orders;
    private OrderOnClickListener orderOnClickListener;

    public OrderAdapter(ArrayList orders, OrderOnClickListener orderOnClickListener) {
        this.orders = orders;
        this.orderOnClickListener = orderOnClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_order, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.title.setText(orders.get(position).getOrder());
        holder.subtitle.setText(orders.get(position).getClient()); // TODO: 14/02/17 implementar campos corretos

        // On Click
        if (orderOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A variável position é final
                    orderOnClickListener.onClickOrder(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orders != null ? orders.size() : 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.article_title)
        public TextView title;

        @Bind(R.id.article_subtitle)
        public TextView subtitle;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OrderOnClickListener {
       void onClickOrder(View view, int idx);
    }
}
// TODO: 10/03/17 PAREI AQUI - VERIFICAR PORQUE SÓ APARECE 1 PEDIDO 