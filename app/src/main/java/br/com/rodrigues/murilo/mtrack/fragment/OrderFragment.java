package br.com.rodrigues.murilo.mtrack.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.dummy.DummyContent;
import br.com.rodrigues.murilo.mtrack.model.Product;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Shows the order detail
 */
public class OrderFragment extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content of this fragment.
     */
    private DummyContent.DummyItem dummyItem;

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

    private MyAdapter myAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            dummyItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

        setHasOptionsMenu(true);
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_order);

        if (dummyItem != null) {
            author.setText(dummyItem.author);
            quote.setText(dummyItem.content);
        }

        // call the Barcode Scanner app
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });

        setupRecycler();

        return rootView;
    }

    // return of Barcode Scanner app
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == ((BaseActivity) getActivity()).RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                barcode.setText(contents);

                // Handle successful scan
            } else if (resultCode == ((BaseActivity) getActivity()).RESULT_CANCELED) {
                // Handle cancel
                barcode.setText("");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // your logic
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        // TODO: 11/02/17 buscar informações do banco
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1"));
        products.add(new Product(2, "Product 2"));
        products.add(new Product(3, "Product 3"));
        products.add(new Product(4, "Product 4"));
        products.add(new Product(5, "Product 5"));
        products.add(new Product(6, "Product 6"));
        products.add(new Product(7, "Product 7"));
        products.add(new Product(8, "Product 8"));
        products.add(new Product(9, "Product 9"));
        products.add(new Product(10, "Product 10"));

        myAdapter = new MyAdapter(products);
        recyclerView.setAdapter(myAdapter);

        // Configurando um divider entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
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


    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        private final List<Product> products;

        public MyAdapter(ArrayList products) {
            this.products = products;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_product_count, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.title.setText(products.get(position).getName());
            holder.count.setText(String.valueOf(products.get(position).getId())); // TODO: 14/02/17 implementar campos corretos
        }

        @Override
        public int getItemCount() {
            return products != null ? products.size() : 0;
        }

        /**
         * Método publico chamado para atualziar a lista.
         *
         * @param product deve ser substituido pela classe
         */
        public void updateList(Product product) {
            insertItem(product);
        }

        // Insert a new product
        private void insertItem(Product product) {
            products.add(product); // TODO: 13/02/17 ajustar
            notifyItemInserted(getItemCount());
        }

        // Update a product
        private void updateItem(int position) {
            Product product = products.get(position);
            //product.incrementRead(); // TODO: 10/02/17 Criar imcrementRead para incrementar quantidade lida do produto
            notifyItemChanged(position);
        }

        // Remove a product
        private void removerItem(int position) {
            products.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, products.size());
        }
    }



    public OrderFragment() {}
}
