package br.com.rodrigues.murilo.mtrack.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.ReturnMessage;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderItemService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;
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
    private ReturnMessage returnMessage = null; // last return method message
    private SalesOrder salesOrder;

    //@Bind(R.id.txtOrder)
    //TextView textOrder;

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

    @Bind(R.id.radio_insert)
    RadioButton radioInsert;

    @Bind(R.id.radio_remove)
    RadioButton radioRemove;

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
                } else {
                    Snackbar.make(view, R.string.message_barcode_scanner, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide the keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(barcode.getWindowToken(), 0);

                if (radioRemove.isChecked()) {
                    returnMessage = salesOrder.removePackage(view.getContext(), String.valueOf(barcode.getText()));
                } else {
                    returnMessage = salesOrder.readPackage(view.getContext(), String.valueOf(barcode.getText()));
                }

                Snackbar.make(view, returnMessage.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                if (returnMessage.isOk()){
                    barcode.setText("");
                    // refresh list
                    myAdapter.notifyDataSetChanged();
                }
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
                buttonRead.performClick();

                if (returnMessage.isOk()) {
                    fab.performClick();
                }

                // Handle successful scan
            } else if (resultCode == ((BaseActivity) getActivity()).RESULT_CANCELED) {
                // Handle cancel
                barcode.setText("");
            }
        }
    }
    // TODO: 29/03/17 [Wish] - Use sound effects on read
    // TODO: 29/03/17 [Wish] - Add Sales Order Delivered info on list

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(!salesOrder.isDelivered());
        menu.getItem(1).setVisible(salesOrder.isDelivered());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message;

        switch (item.getItemId()) {
            case R.id.action_finish_order:
                message = salesOrder.finishOrder(getActivity());
                Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            case R.id.action_open_order:
                message = salesOrder.OpenOrder(getActivity());
                Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
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