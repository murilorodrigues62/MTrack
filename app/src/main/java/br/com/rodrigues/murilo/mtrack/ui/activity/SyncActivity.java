package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    public void onFabClicked(View view) {

       progress = ProgressDialog.show(this, "Synchronizing", "Updating database...", true, false);
       sync();
        //Snackbar.make(view, "Sync finished", Snackbar.LENGTH_LONG).setAction("Action", null).show(); // TODO: 02/03/17 Criar AsyncTask e incluir snackbar ao terminar processamento
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

    public void sync(){

        new Thread() {
            @Override
            public void run() {

                try {
                    // TODO: 02/03/17 Change to sync data with server
                    sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // close the ProgressDialog
                    progress.dismiss();
                }
            }

        }.start();
    }
}
// TODO: 02/03/17 [Wish] Use Preference layout (PreferenceScreen, SwitchPreference)