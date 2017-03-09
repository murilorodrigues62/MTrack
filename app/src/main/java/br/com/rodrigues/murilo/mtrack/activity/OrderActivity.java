package br.com.rodrigues.murilo.mtrack.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.activity.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.fragment.OrderFragment;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setupToolbar();


        OrderFragment fragment =  OrderFragment.newInstance(getIntent().getStringExtra(OrderFragment.ARG_ITEM_ID));

        getFragmentManager().beginTransaction().replace(R.id.activity_order_container, fragment).commit();

    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

}
