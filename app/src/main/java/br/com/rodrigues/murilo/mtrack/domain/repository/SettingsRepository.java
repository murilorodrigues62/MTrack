package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Settings;
import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;

public class SettingsRepository {
    // Name in DataBase
    public static final String TABLE = "SETTINGS";
    public static final String IDSETTINGS = "IDSETTINGS";
    public static final String TRANSPORTERCODE = "TRANSPORTERCODE";
    public static final String URLEDATA = "URLEDATA";
    private static final String[] ALLCOLUMNS = {IDSETTINGS, TRANSPORTERCODE, URLEDATA};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public SettingsRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public Settings findSettings(){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, IDSETTINGS);
            List<Settings> settings = toList(cursor);
            return settings.isEmpty() ? null : settings.get(0);
        } finally {
            database.close();
        }
    }

    // Read cursor and create list
    private List<Settings> toList(Cursor cursor) {
        List<Settings> Settings = new ArrayList<Settings>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Settings settings = new Settings();

            for(int i = 0; i < cursor.getColumnCount(); i++){
                switch (cursor.getColumnName(i)){
                    case IDSETTINGS:
                        settings.setIdSettings(cursor.getInt(i));
                        break;
                    case TRANSPORTERCODE:
                        settings.setTransporterCode(cursor.getString(i));
                        break;
                    case URLEDATA:
                        settings.setUrlEdata(cursor.getString(i));
                        break;
                }
            }

            Settings.add(settings);
            cursor.moveToNext();
        }
        return Settings;
    }

    public int update(Settings settings) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRANSPORTERCODE, settings.getTransporterCode());
        values.put(URLEDATA, settings.getUrlEdata());

        String where = IDSETTINGS + " =? ";

        String[] whereArgs = {String.valueOf(settings.getIdSettings())};

        try {
            int count = database.update(TABLE, values, where, whereArgs);
            return count;
        } finally {
            database.close();
        }
    }
}
