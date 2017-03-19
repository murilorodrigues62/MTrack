package br.com.rodrigues.murilo.mtrack.domain.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.rodrigues.murilo.mtrack.domain.repository.CustomerRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.ProductRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderItemRepository;
import br.com.rodrigues.murilo.mtrack.domain.repository.SalesOrderPackageRepository;
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

    // Create table MATERIAL_EMBALAGEM
    public static final String CREATE_PRODUCT = "CREATE TABLE "+ ProductRepository.TABLE +" (" +
            ProductRepository.IDPRODUCT   + " INTEGER NOT NULL PRIMARY KEY, " +
            ProductRepository.PRODUCTCODE + " TEXT NOT NULL,                " +
            ProductRepository.PRODUCTNAME + " TEXT NOT NULL);               ";

    // Create table PEDIDO_VENDA
    public static final String CREATE_SALES_ORDER = "CREATE TABLE " + SalesOrderRepository.TABLE +" (" +
            SalesOrderRepository.IDSALESORDER + " INTEGER NOT NULL PRIMARY KEY, " +
            SalesOrderRepository.IDDELIVERY   + " INTEGER NOT NULL,             " +
            SalesOrderRepository.IDCUSTOMER   + " INTEGER NOT NULL,             " +
            SalesOrderRepository.DELIVERED    + " INTEGER DEFAULT 0);           ";

    // Create table PEDIDO_VENDA_ITEM
    public static final String CREATE_SALES_ORDER_ITEM = "CREATE TABLE " + SalesOrderItemRepository.TABLE +" (" +
            SalesOrderItemRepository.IDSALESORDERITEM + " INTEGER NOT NULL PRIMARY KEY, " +
            SalesOrderItemRepository.IDSALESORDER     + " INTEGER NOT NULL,             " +
            SalesOrderItemRepository.IDPRODUCT        + " INTEGER NOT NULL,             " +
            SalesOrderItemRepository.QUANTITY         + " INTEGER DEFAULT 0);           ";

    // Create table EXPEDICAO_CARGA_IDENTIFICACAO
    public static final String CREATE_SALES_ORDER_PACKAGE = "CREATE TABLE "+ SalesOrderPackageRepository.TABLE +" (" +
            SalesOrderPackageRepository.IDDELIVERY        + " INTEGER NOT NULL PRIMARY KEY, " +
            SalesOrderPackageRepository.IDSALESORDER      + " INTEGER,                      " +
            SalesOrderPackageRepository.IDPRODUCT         + " INTEGER NOT NULL,             " +
            SalesOrderPackageRepository.IDSALESORDERREAL  + " INTEGER NOT NULL,             " +
            SalesOrderPackageRepository.BARCODE           + " TEXT NOT NULL);               ";

    public static final String INSERT_CUSTOMER(){
        return "INSERT INTO "+ CustomerRepository.TABLE +
                " (ID_CLIENTE, NM_CLIENTE) VALUES      " +
                " (2036, 'SHOPPING FRUTAS GUARANY'),   " +
                " (2037, 'SUPERMERCADO LIDER LTDA'),   " +
                " (2038, 'CASA DE CARNES POPULAR'),    " +
                " (2039, 'AA COMERCIO DE CARNES DE SANTA BARBARA LTDA'); " ;
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

    // insert fake data
    public static final String INSERT_SALES_ORDER() {
        return "INSERT INTO " + SalesOrderRepository.TABLE +
               " (ID_PEDIVEND, ID_CARGEXPE, ID_CLIENTE) VALUES " +
               " (279728, 29797, 2036),  " +
               " (279729, 29797, 2037),  " +
               " (279730, 29797, 2038),  " +
               " (279731, 29797, 2039),  " +
               " (279732, 29798, 2036);  " ;
    }

    // insert fake data
    public static final String INSERT_SALES_ORDER_ITEM() {
        return "INSERT INTO " + SalesOrderItemRepository.TABLE +
                " (ID_ITEMPEDIVEND, ID_PEDIVEND, ID_MATEEMBA, NR_EMBAEXPE) VALUES " +
                " (1, 279728, 1, 2),  " +
                " (2, 279728, 2, 2),  " +
                " (3, 279728, 3, 4),  " +
                " (4, 279728, 4, 2),  " +
                " (5, 279728, 5, 1),  " +
                " (6, 279728, 6, 2),  " +
                " (7, 279729, 2, 2),  " +
                " (8, 279729, 4, 10), " +
                " (9, 279730, 1, 2),  " +
                " (10,279730, 6, 2),  " +
                " (11,279730, 5, 2),  " +
                " (12,279731, 1, 5),  " +
                " (13,279732, 3, 20); " ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute scripts to create database
        sqLiteDatabase.execSQL(CREATE_CUSTOMER);
        sqLiteDatabase.execSQL(CREATE_PRODUCT);
        sqLiteDatabase.execSQL(CREATE_SALES_ORDER);
        sqLiteDatabase.execSQL(CREATE_SALES_ORDER_ITEM);
        sqLiteDatabase.execSQL(CREATE_SALES_ORDER_PACKAGE);

        sqLiteDatabase.execSQL(INSERT_CUSTOMER());
        sqLiteDatabase.execSQL(INSERT_PRODUCT());
        sqLiteDatabase.execSQL(INSERT_SALES_ORDER());
        sqLiteDatabase.execSQL(INSERT_SALES_ORDER_ITEM());

        // TODO: 19/03/17 add data to package and config table 
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
