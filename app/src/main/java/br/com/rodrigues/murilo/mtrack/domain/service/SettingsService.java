package br.com.rodrigues.murilo.mtrack.domain.service;

import android.content.Context;

import br.com.rodrigues.murilo.mtrack.domain.model.Settings;
import br.com.rodrigues.murilo.mtrack.domain.repository.SettingsRepository;

public class SettingsService {

    // Get Settings from local Database
    public static Settings findSettings(Context context) {
        SettingsRepository db = new SettingsRepository(context);
        Settings settings = db.findSettings();
        return settings;
    }

    public static boolean update(Context context, Settings settings){
        SettingsRepository db = new SettingsRepository(context);
        return db.update(settings) == 1;
    }
}
