package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.Settings;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderPackageService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;
import br.com.rodrigues.murilo.mtrack.infra.service.SettingsService;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

/**
 * System Settings
 */
public class SyncActivity extends BaseActivity {
    private ProgressDialog progress;

    @Bind(R.id.switchUpdate)
    Switch switchUpdate;

    @Bind(R.id.switchSend)
    Switch switchSend;

    @Bind(R.id.switchClear)
    Switch switchClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @OnClick(R.id.fab)
    public void onFabClicked(final View view) {

        if (!switchUpdate.isChecked() && !switchSend.isChecked() && !switchClear.isChecked()) return;

       progress = ProgressDialog.show(this, getString(R.string.synchronizing), getString(R.string.updating_database), true, false);

        AsyncTask<Boolean, Void, Boolean> task = new AsyncTask<Boolean, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Boolean... booleans) {

                try {

                    // Get the context and Settings
                    Context context = getApplicationContext();
                    Settings settings = SettingsService.findSettings(context);

                    // If user chose send data
                    if (booleans[1]) {
                        // add
                        SalesOrderPackageService.putSalesOrderPackageWS(context, settings);
                        sleep(1000);
                    }

                    // If user chose clear data base
                    if (booleans[2]) {
                        SalesOrderPackageService.clearDataBase(context);
                        sleep(1000);
                    }

                    // If user chose update data
                    if (booleans[0]) {
                        // Sync orders and packages
                        SalesOrderService.getSalesOrderWS(context, settings);
                        SalesOrderPackageService.getSalesOrderPackageWS(context, settings);
                        sleep(1000);
                    }

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

        task.execute(switchUpdate.isChecked(), switchSend.isChecked(), switchClear.isChecked());
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