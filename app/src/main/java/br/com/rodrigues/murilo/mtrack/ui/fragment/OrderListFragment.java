package br.com.rodrigues.murilo.mtrack.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.ui.activity.OrderActivity;
import br.com.rodrigues.murilo.mtrack.ui.adapter.OrderAdapter;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseFragment;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderService;
import butterknife.Bind;

/**
 * Shows a list of all available quotes.
 */
public class OrderListFragment extends BaseFragment {

    @Bind(R.id.recyclerview_order_list)
    RecyclerView recyclerView;

    private OrderAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_order_list);

        setupRecycler();

        return rootView;
    }

    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();
        return fragment;
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ArrayList<SalesOrder> salesOrders = (ArrayList<SalesOrder>) SalesOrderService.findAllGrouped(getActivity());

        myAdapter = new OrderAdapter(salesOrders, onClick());
        recyclerView.setAdapter(myAdapter);

        // Configurando um divider entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private OrderAdapter.OrderOnClickListener onClick() {
        return new OrderAdapter.OrderOnClickListener() {
            @Override
            public void onClickOrder(View view, long id) {
                Intent detailIntent = null;
                detailIntent = new Intent(getActivity(), OrderActivity.class);
                detailIntent.putExtra(OrderFragment.ORDER_ID, id);
                startActivity(detailIntent);
            }
        };
    }



}
