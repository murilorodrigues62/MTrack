package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.ui.fragment.SalesOrderListFragment;
import butterknife.ButterKnife;

/**
 * Lists all sales order
 */
public class SalesOrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_list);
        ButterKnife.bind(this);
        setupToolbar();

        SalesOrderListFragment fragment =  SalesOrderListFragment.newInstance();
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
