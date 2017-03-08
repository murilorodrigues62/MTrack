package br.com.rodrigues.murilo.mtrack.activity;

import android.os.Bundle;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.fragment.OrderFragment;
import br.com.rodrigues.murilo.mtrack.activity.base.BaseActivity;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Show the Up button in the action bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        OrderFragment fragment =  OrderFragment.newInstance(getIntent().getStringExtra(OrderFragment.ARG_ITEM_ID));
        getFragmentManager().beginTransaction().replace(R.id.activity_order_container, fragment).commit();
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }



}
