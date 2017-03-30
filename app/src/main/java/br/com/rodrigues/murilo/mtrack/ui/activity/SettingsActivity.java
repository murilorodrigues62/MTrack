package br.com.rodrigues.murilo.mtrack.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.rodrigues.murilo.mtrack.R;
import br.com.rodrigues.murilo.mtrack.domain.model.Settings;
import br.com.rodrigues.murilo.mtrack.domain.service.SettingsService;
import br.com.rodrigues.murilo.mtrack.ui.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * System Settings
 */
public class SettingsActivity extends BaseActivity {
    private Settings settings;

    @Bind(R.id.settings_transporter)
    TextView textTransporter;

    @Bind(R.id.settings_webservice)
    TextView textWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @Override
    protected void onStart() {
        settings = SettingsService.findSettings(this);
        textTransporter.setText(settings.getTransporterCode());
        textWebService.setText(settings.getUrlEdata());

        super.onStart();
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        settings.setTransporterCode(String.valueOf(textTransporter.getText()));
        settings.setUrlEdata(String.valueOf(textWebService.getText()));

        String message = SettingsService.update(this, settings) ? getString(R.string.message_saved) : getString(R.string.error_not_saved);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
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

