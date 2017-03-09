package br.com.rodrigues.murilo.mtrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.activity.base.BaseActivity;
import br.com.rodrigues.murilo.mtrack.fragment.OrderFragment;
import br.com.rodrigues.murilo.mtrack.fragment.OrderListFragment;

/**
 * Lists all sales order
 */
public class ListOrderActivity extends BaseActivity implements OrderListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list); 

        setupToolbar();
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(String id) {
        // Start the detail activity of order
        Intent detailIntent = new Intent(this, OrderActivity.class);
        detailIntent.putExtra(OrderFragment.ARG_ITEM_ID, id);
        startActivity(detailIntent);
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

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_sales_order;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
