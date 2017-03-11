package br.com.rodrigues.murilo.mtrack.domain.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.rodrigues.murilo.mtrack.domain.repository.OrderRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.ProductRepository;


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
    public static final String CREATE_ORDER_V1 = "CREATE TABLE "+ OrderRepository.PEDIDO_VENDA +" (" +
            OrderRepository.ID_PEDIVEND + " INTEGER PRIMARY KEY, " +
            OrderRepository.ID_CARGEXPE + " TEXT, " +
            OrderRepository.NM_CLIENTE  + " TEXT);";

    // Create table MATERIAL_EMBALAGEM
    public static final String CREATE_PRODUCT_V1 = "CREATE TABLE "+ ProductRepository.MATERIAL_EMBALAGEM +" (" +
            ProductRepository.ID_MATEEMBA + " INTEGER PRIMARY KEY, " +
            ProductRepository.ID_PRODMATEEMBA + " TEXT, " +
            ProductRepository.NM_PRODMATEEMBA  + " TEXT);";

    // insert fake data
    public static final String INSERT_ORDER(Integer id) {
        return "INSERT INTO " + OrderRepository.PEDIDO_VENDA + " ( " +
                OrderRepository.ID_PEDIVEND + " , " +
                OrderRepository.ID_CARGEXPE + " , " +
                OrderRepository.NM_CLIENTE + " ) VALUES ( " +
                id.toString() + " , " +
                " '1', " +
                " 'Client " + id.toString() + "');";

    }

    // insert fake data
    public static final String INSERT_PRODUCT(Integer id) {
        return "INSERT INTO " + ProductRepository.MATERIAL_EMBALAGEM + " ( " +
                ProductRepository.ID_MATEEMBA + " , " +
                ProductRepository.ID_PRODMATEEMBA + " , " +
                ProductRepository.NM_PRODMATEEMBA + " ) VALUES ( " +
                id.toString() + " , " +
                "'00'" + id.toString() + " , " +
                " 'Product " + id.toString() + "');";

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute scripts to create database

        sqLiteDatabase.execSQL(CREATE_ORDER_V1);

        for(int i = 1; i <=10; i++){
            sqLiteDatabase.execSQL(INSERT_ORDER(i));

            if (i <= 4) {
                sqLiteDatabase.execSQL(INSERT_ORDER(i));
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
