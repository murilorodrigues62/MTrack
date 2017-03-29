package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.ui.fragment.SalesOrderFragment;
import butterknife.ButterKnife;

public class SalesOrderActivity extends BaseActivity {
    private int idSalesOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        ButterKnife.bind(this);

        idSalesOrder = getIntent().getIntExtra(SalesOrderFragment.ORDER_ID,0);

        SalesOrderFragment fragment =  SalesOrderFragment.newInstance(idSalesOrder);
        getFragmentManager().beginTransaction().replace(R.id.activity_order_container, fragment).commit();

        setupToolbar();

    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(ab.getTitle() + " " + String.valueOf(idSalesOrder));
    }
}

// TODO: 15/03/17 Implementar quantidade digitos da identificação para ler automatico
// TODO: 18/03/17 exibir pedido e cliente no title e subtitle da tollbar (subtitle não está aparecendo)