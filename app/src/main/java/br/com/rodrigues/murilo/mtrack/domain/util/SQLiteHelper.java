package br.com.rodrigues.murilo.mtrack.domain.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.rodrigues.murilo.mtrack.domain.repository.CustomerRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.ProductRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderRepository;


public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mtrack.db";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // Create table CLIENTE
    public static final String CREATE_CUSTOMER = "CREATE TABLE "+ CustomerRepository.TABLE +" (" +
            CustomerRepository.IDCUSTOMER   + " INTEGER NOT NULL PRIMARY KEY, " +
            CustomerRepository.CUSTOMERNAME + " TEXT NOT NULL)                ";

    // Create table PEDIDO_VENDA
    public static final String CREATE_ORDER = "CREATE TABLE " + SalesOrderRepository.TABLE +" (" +
            SalesOrderRepository.IDSALESORDER + " INTEGER NOT NULL PRIMARY KEY, " +
            SalesOrderRepository.IDDELIVERY   + " INTEGER NOT NULL,             " +
            SalesOrderRepository.IDCUSTOMER   + " INTEGER NOT NULL,             " +
            SalesOrderRepository.DELIVERED    + " INTEGER DEFAULT 0);           ";

    // Create table MATERIAL_EMBALAGEM
    public static final String CREATE_PRODUCT = "CREATE TABLE "+ ProductRepository.TABLE +" (" +
            ProductRepository.IDPRODUCT   + " INTEGER NOT NULL PRIMARY KEY, " +
            ProductRepository.PRODUCTCODE + " TEXT NOT NULL,                " +
            ProductRepository.PRODUCTNAME + " TEXT NOT NULL);               ";


    public static final String INSERT_CUSTOMER(){
        return "INSERT INTO "+ CustomerRepository.TABLE +
                " (ID_CLIENTE, NM_CLIENTE) VALUES      " +
                " (2036, 'SHOPPING FRUTAS GUARANY'),   " +
                " (2037, 'SUPERMERCADO LIDER LTDA'),   " +
                " (2038, 'CASA DE CARNES POPULAR'),    " +
                " (2039, 'A COMERCIO DE CARNES LTDA'); " ;
    }

    // insert fake data
    public static final String INSERT_ORDER() {
        return "INSERT INTO " + SalesOrderRepository.TABLE +
               " (ID_PEDIVEND, ID_CARGEXPE, ID_CLIENTE) VALUES " +
               " (279728, 29797, 2036),  " +
               " (279729, 29797, 2037),  " +
               " (279730, 29797, 2038),  " +
               " (279731, 29797, 2039),  " +
               " (279732, 29798, 2036);  " ;
    }

    // insert fake data
    public static final String INSERT_PRODUCT() {
        return "INSERT INTO " + ProductRepository.TABLE + " ( " +
                ProductRepository.IDPRODUCT   + " , " +
                ProductRepository.PRODUCTCODE + " , " +
                ProductRepository.PRODUCTNAME + " ) VALUES " +
                "(1, '0001', 'ASA RESFRIADA'), " +
                "(2, '0101', 'PEITO CONGELADO'), " +
                "(3, '0005', 'FRANGO INTEIRO'), " +
                "(4, '0012', 'PE CONGELADO'), " +
                "(5, '0031', 'MEIO DA ASA'), " +
                "(6, '9999', 'CORACAO RESFRIADO');";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute scripts to create database
        sqLiteDatabase.execSQL(CREATE_CUSTOMER);
        sqLiteDatabase.execSQL(CREATE_ORDER);
        sqLiteDatabase.execSQL(CREATE_PRODUCT);

        // TODO: 18/03/17 PAREI AQUI - CRIAR TABELA ITEM PEDIDO 

        sqLiteDatabase.execSQL(INSERT_CUSTOMER());
        sqLiteDatabase.execSQL(INSERT_ORDER());
        sqLiteDatabase.execSQL(INSERT_PRODUCT());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
