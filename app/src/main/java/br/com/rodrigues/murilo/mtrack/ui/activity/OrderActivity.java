package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.ui.fragment.OrderFragment;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setupToolbar();

        OrderFragment fragment =  OrderFragment.newInstance(getIntent().getLongExtra(OrderFragment.ORDER_ID,0));
        getFragmentManager().beginTransaction().replace(R.id.activity_order_container, fragment).commit();

    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}

// TODO: 15/03/17 Implementar quantidade digitos da identificação para ler automatico