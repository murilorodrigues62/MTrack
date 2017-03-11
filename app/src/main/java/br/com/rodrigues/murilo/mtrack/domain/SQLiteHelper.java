package br.com.rodrigues.murilo.mtrack.domain;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtrack.db";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // Create table PEDIDO_VENDA
    public static final String CREATE_ORDER_V1 = "CREATE TABLE "+ OrderDB.PEDIDO_VENDA +" (" +
            OrderDB.ID_PEDIVEND + " INTEGER PRIMARY KEY, " +
            OrderDB.ID_CARGEXPE + " TEXT, " +
            OrderDB.NM_CLIENTE  + " TEXT);";

    public static final String INSERT_ORDER(Integer id) {
        return "INSERT INTO " + OrderDB.PEDIDO_VENDA + " ( " +
                OrderDB.ID_PEDIVEND + " , " +
                OrderDB.ID_CARGEXPE + " , " +
                OrderDB.NM_CLIENTE + " ) VALUES ( " +
                id.toString() + " , " +
                " '1', " +
                " 'Client " + id.toString() + "');";

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ORDER_V1);

        for(int i = 1; i <=10; i++){
            sqLiteDatabase.execSQL(INSERT_ORDER(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
