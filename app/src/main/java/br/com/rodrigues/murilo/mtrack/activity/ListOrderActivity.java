package br.com.rodrigues.murilo.mtrack.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.fragment.OrderListFragment;
import butterknife.ButterKnife;

/**
 * Lists all sales order
 */
public class ListOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        setupToolbar();

        OrderListFragment fragment =  OrderListFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.activity_order_list, fragment).commit();
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
