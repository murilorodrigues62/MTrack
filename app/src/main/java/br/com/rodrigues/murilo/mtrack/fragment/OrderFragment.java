package br.com.rodrigues.murilo.mtrack.fragment;

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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.adapter.ProductCountAdapter;
import br.com.rodrigues.murilo.mtrack.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.base.BaseFragment;
import br.com.rodrigues.murilo.mtrack.dummy.DummyOrder;
import br.com.rodrigues.murilo.mtrack.dummy.DummyProduct;
import br.com.rodrigues.murilo.mtrack.model.Order;
import br.com.rodrigues.murilo.mtrack.model.Product;
import butterknife.Bind;

/**
 * Shows the order detail
 */
public class OrderFragment extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String QR_CODE_MODE = "QR_CODE_MODE";
    public static final String SCAN_RESULT = "SCAN_RESULT";

    /**
     * The dummy content of this fragment.
     */
    private Order dummyOrder;


    @Bind(R.id.quote)
    TextView quote;

    @Bind(R.id.author)
    TextView author;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.barcode)
    EditText barcode;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    private ProductCountAdapter myAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            dummyOrder = DummyOrder.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

        setHasOptionsMenu(true);
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_order);

        if (dummyOrder != null) {
            author.setText(dummyOrder.getOrder());
            quote.setText(dummyOrder.getClient());
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

    public static OrderFragment newInstance(String itemID) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(OrderFragment.ARG_ITEM_ID, itemID);
        fragment.setArguments(args);
        return fragment;
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ArrayList<Product> products = (ArrayList<Product>) DummyProduct.ITEMS; // TODO: 11/02/17 buscar informações do banco filtrando por order

        myAdapter = new ProductCountAdapter(products);
        recyclerView.setAdapter(myAdapter);

        // Configurando um divider entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    public OrderFragment() {}
}
