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
import br.com.rodrigues.murilo.mtrack.ui.activity.SalesOrderActivity;
import br.com.rodrigues.murilo.mtrack.ui.adapter.SalesOrderAdapter;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseFragment;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;
import butterknife.Bind;

/**
 * Shows a list of all available quotes.
 */
public class SalesOrderListFragment extends BaseFragment {

    @Bind(R.id.recyclerview_order_list)
    RecyclerView recyclerView;

    private SalesOrderAdapter myAdapter;
    private ArrayList<SalesOrder> salesOrders = new ArrayList<SalesOrder>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_sales_order_list);

        setupRecycler();

        return rootView;
    }

    public static SalesOrderListFragment newInstance() {
        SalesOrderListFragment fragment = new SalesOrderListFragment();
        return fragment;
    }

    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        myAdapter = new SalesOrderAdapter(salesOrders, onClick());
        recyclerView.setAdapter(myAdapter);

        // Config a line divider decoration
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private SalesOrderAdapter.OrderOnClickListener onClick() {
        return new SalesOrderAdapter.OrderOnClickListener() {
            @Override
            public void onClickOrder(View view, int id) {
                Intent detailIntent = null;
                detailIntent = new Intent(getActivity(), SalesOrderActivity.class);
                detailIntent.putExtra(SalesOrderFragment.ORDER_ID, id);
                startActivity(detailIntent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        salesOrders.clear();
        salesOrders.addAll(SalesOrderService.findAll(getActivity()));
        myAdapter.notifyDataSetChanged();
    }
}
