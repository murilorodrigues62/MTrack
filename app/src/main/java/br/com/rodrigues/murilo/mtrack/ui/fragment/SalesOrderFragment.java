package br.com.rodrigues.murilo.mtrack.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderItemService;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderService;
import br.com.rodrigues.murilo.mtrack.ui.adapter.SalesOrderItemAdapter;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseFragment;
import butterknife.Bind;

/**
 * Shows the salesOrder detail
 */
public class SalesOrderFragment extends BaseFragment {
    public static final String ORDER_ID = "ORDER_ID";
    public static final String QR_CODE_MODE = "QR_CODE_MODE";
    public static final String SCAN_RESULT = "SCAN_RESULT";

    private SalesOrder salesOrder;

    @Bind(R.id.txtOrder)
    TextView textOrder;

    @Bind(R.id.txtClient)
    TextView textClient;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.barcode)
    EditText barcode;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.buttonRead)
    Button buttonRead;

    private SalesOrderItemAdapter myAdapter;

    public SalesOrderFragment() {}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ORDER_ID)) {
            salesOrder = SalesOrderService.findById(getActivity(), getArguments().getInt(ORDER_ID));
        }
        setHasOptionsMenu(true);
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_sales_order);

        if (salesOrder != null) {
            textOrder.setText(salesOrder.toString());
            textClient.setText(salesOrder.getCustomer().getCustomerName());
        }

        // call the Barcode Scanner app
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getString(R.string.intent_barcode_scanner));
                intent.putExtra(getString(R.string.intent_barcode_extra), QR_CODE_MODE);

                PackageManager manager = view.getContext().getPackageManager();
                if (manager.resolveActivity(intent, 0) != null){
                    startActivityForResult(intent, 0);
                } else
                {
                    Snackbar.make(view, R.string.message_barcode_scanner, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 21/03/17 CONTINUE...
                String message = salesOrder.readPackage(view.getContext(), String.valueOf(barcode.getText()));
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        setupRecycler();
        return rootView;
    }

    // return of Barcode Scanner app
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == ((BaseActivity) getActivity()).RESULT_OK) {
                String contents = intent.getStringExtra(SCAN_RESULT);
                //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                barcode.setText(contents);

                // Handle successful scan
            } else if (resultCode == ((BaseActivity) getActivity()).RESULT_CANCELED) {
                // Handle cancel
                barcode.setText("");
            }
        }
    }

    public static SalesOrderFragment newInstance(int itemID) {
        SalesOrderFragment fragment = new SalesOrderFragment();
        Bundle args = new Bundle();
        args.putInt(SalesOrderFragment.ORDER_ID, itemID);
        fragment.setArguments(args);
        return fragment;
    }

    private void setupRecycler() {

        // Config layout manager to create products list
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Adapter to add products in list
        ArrayList<SalesOrderItem> salesOrderItems = (ArrayList<SalesOrderItem>) SalesOrderItemService.findByIdOrder(getActivity(), salesOrder.getIdSalesOrder());

        myAdapter = new SalesOrderItemAdapter(salesOrderItems);
        recyclerView.setAdapter(myAdapter);

        // Add divider line
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
}

// TODO: 11/03/17 ajustar layout do card contendo dados do pedido, para ficar menor.