package br.com.rodrigues.murilo.mtrack.domain.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderRepository;
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
    public static final String CREATE_ORDER_V1 = "CREATE TABLE " + SalesOrderRepository.PEDIDO_VENDA +" (" +
            SalesOrderRepository.ID_PEDIVEND     + " DECIMAL(12,0), " +
            SalesOrderRepository.ID_CARGEXPE     + " DECIMAL(12,0), " +
            SalesOrderRepository.ID_CLIENTE      + " DECIMAL(12,0), " +
            SalesOrderRepository.NM_CLIENTE      + " VARCHAR(255),  " +
            SalesOrderRepository.ID_ITEMPEDIVEND + " DECIMAL(12,0), " +
            SalesOrderRepository.ID_MATEEMBA     + " DECIMAL(12,0), " +
            SalesOrderRepository.ID_PRODMATEEMBA + " VARCHAR(255),  " +
            SalesOrderRepository.NM_PRODMATEEMBA + " VARCHAR(255),  " +
            SalesOrderRepository.NR_EMBAEXPE     + " DECIMAL(12,0), " +
            SalesOrderRepository.FL_DELIVERED    + " INTEGER );     ";

    // Create table MATERIAL_EMBALAGEM
    public static final String CREATE_PRODUCT_V1 = "CREATE TABLE "+ ProductRepository.MATERIAL_EMBALAGEM +" (" +
            ProductRepository.ID_MATEEMBA + " INTEGER PRIMARY KEY, " +
            ProductRepository.ID_PRODMATEEMBA + " TEXT, " +
            ProductRepository.NM_PRODMATEEMBA  + " TEXT);";

    // insert fake data
    public static final String INSERT_ORDER() {
        return "INSERT INTO " + SalesOrderRepository.PEDIDO_VENDA +
               " (ID_PEDIVEND, ID_CARGEXPE, ID_CLIENTE, NM_CLIENTE, ID_ITEMPEDIVEND, ID_MATEEMBA, " +
               "  ID_PRODMATEEMBA, NM_PRODMATEEMBA, NR_EMBAEXPE) VALUES " +
               " (279728, 29797, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066290, 13, '0005', 'COXA/SOBRE RF'  , 2) ,  " +
               " (279728, 29797, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066291, 14, '0006', 'ASA RF'         , 3) ,  " +
               " (279728, 29797, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066292, 15, '0007', 'FRANGO INTEIRO ', 5) ,  " +
               " (279728, 29797, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066293, 16, '0008', 'PEITO CONGELADO', 1) ,  " +
               " (279728, 29797, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066294, 17, '0009', 'CORACAO'        , 1) ,  " +
               " (279729, 29797, 2037, 'SUPERMERCADO LIDER LTDA.'     , 1066295, 13, '0005', 'COXA/SOBRE RF'  , 10),  " +
               " (279730, 29797, 2038, 'CASA DE CARNES POPULAR LTDA.' , 1066296, 13, '0005', 'COXA/SOBRE RF'  , 3) ,  " +
               " (279730, 29797, 2038, 'CASA DE CARNES POPULAR LTDA.' , 1066297, 13, '0007', 'FRANGO INTEIRO', 3)  ,  " +
               " (279730, 29797, 2038, 'CASA DE CARNES POPULAR LTDA.' , 1066298, 13, '0006', 'ASA RF'        , 3)  ,  " +
               " (279731, 29797, 2039, 'AA COMERCIO DE CARNES LTDA.'  , 1066299, 13, '0005', 'COXA/SOBRE RF' , 1)  ,  " +
               " (279731, 29797, 2039, 'AA COMERCIO DE CARNES LTDA.'  , 1066300, 13, '0006', 'ASA RF'        , 3)  ,  " +
               " (279731, 29797, 2039, 'AA COMERCIO DE CARNES LTDA.'  , 1066301, 13, '0007', 'FRANGO INTEIRO', 5)  ,  " +
               " (279731, 29797, 2039, 'AA COMERCIO DE CARNES LTDA.'  , 1066302, 13, '0008', 'PEITO CONGELADO', 1) ,  " +
               " (279731, 29797, 2039, 'AA COMERCIO DE CARNES LTDA.'  , 1066303, 13, '0009', 'CORACAO'        , 1) ,  " +
               " (279732, 29798, 2036, 'SHOPPING FRUTAS GUARANY LTDA.', 1066305, 13, '0005', 'COXA/SOBRE RF'  , 1);   ";

    }

    // insert fake data
    public static final String INSERT_PRODUCT(Integer id) {
        return "INSERT INTO " + ProductRepository.MATERIAL_EMBALAGEM + " ( " +
                ProductRepository.ID_MATEEMBA + " , " +
                ProductRepository.ID_PRODMATEEMBA + " , " +
                ProductRepository.NM_PRODMATEEMBA + " ) VALUES ( " +
                id.toString() + " , " +
                "'00" + id.toString() + "' , " +
                " 'Product " + id.toString() + "');";

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute scripts to create database

        sqLiteDatabase.execSQL(CREATE_ORDER_V1);
        sqLiteDatabase.execSQL(CREATE_PRODUCT_V1);

        sqLiteDatabase.execSQL(INSERT_ORDER());

        for(int i = 1; i <=10; i++){


            if (i <= 4) {
                sqLiteDatabase.execSQL(INSERT_PRODUCT(i));
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
