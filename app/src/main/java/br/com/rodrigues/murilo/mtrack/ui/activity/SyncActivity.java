package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

/**
 * System Settings
 */
public class SyncActivity extends BaseActivity {
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @OnClick(R.id.fab)
    public void onFabClicked(final View view) {

       progress = ProgressDialog.show(this, getString(R.string.synchronizing), getString(R.string.updating_database), true, false);

        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {

                try {
                    SalesOrderService.syncSalesOrder(getApplicationContext());
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                progress.dismiss();
                Snackbar.make(view, R.string.sync_finished, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                super.onPostExecute(aBoolean);
            }
        };

        task.execute();
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
// TODO: 02/03/17 [Wish] Use Preference layout (PreferenceScreen, SwitchPreference)