package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderPackage;
import br.com.rodrigues.murilo.mtrack.domain.service.ProductService;
import br.com.rodrigues.murilo.mtrack.domain.service.SalesOrderService;
import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;

public class SalesOrderPackageRepository {
    // Name in DataBase
    public static final String TABLE = "EXPEDICAO_CARGA_IDENTIFICACAO";
    public static final String IDDELIVERY = "ID_CARGEXPE";
    public static final String IDSALESORDER = "ID_PEDIVEND";
    public static final String IDPRODUCT = "ID_MATEEMBA";
    public static final String BARCODE = "ID_IDENREGIPROD";
    public static final String IDSALESORDERREAL = "ID_PEDIVEND_REAL";
    private static final String[] ALLCOLUMNS = {IDDELIVERY, IDSALESORDER, IDPRODUCT, BARCODE, IDSALESORDERREAL};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public SalesOrderPackageRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<SalesOrderPackage> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, IDPRODUCT);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public List<SalesOrderPackage> findBySalesOrder(int idSalesOrder){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    IDSALESORDER + " = ?", new String[]{String.valueOf(idSalesOrder)}, null, null, null);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public List<SalesOrderPackage> findBySalesOrderReal(int idSalesOrder){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    IDSALESORDERREAL + " = ?", new String[]{String.valueOf(idSalesOrder)}, null, null, null);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public SalesOrderPackage findByBarCode(int idDelivery, String barcode){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    BARCODE + " = ? AND " + IDDELIVERY + " = ? ", new String[]{barcode, String.valueOf(idDelivery)}, null, null, null);

            List<SalesOrderPackage> salesOrderPackages = toList(cursor);
            return salesOrderPackages.isEmpty() ? null : salesOrderPackages.get(0);
        } finally {
            database.close();
        }
    }

    // TODO: 19/03/17 Add update SalesOrder

    // Read cursor and create list
    private List<SalesOrderPackage> toList(Cursor cursor) {
        List<SalesOrderPackage> salesOrderPackages = new ArrayList<SalesOrderPackage>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SalesOrderPackage salesOrderPackage = new SalesOrderPackage();

            for(int i = 0; i < cursor.getColumnCount(); i++){
                switch (cursor.getColumnName(i)){
                    case IDPRODUCT:
                        salesOrderPackage.setProduct(ProductService.findById(this.context, cursor.getInt(i)));
                        break;
                    case IDSALESORDER:
                        salesOrderPackage.setSalesOrder(SalesOrderService.findById(this.context, cursor.getInt(i)));
                        break;
                    case IDSALESORDERREAL:
                        salesOrderPackage.setSalesOrderReal(SalesOrderService.findById(this.context, cursor.getInt(i)));
                        break;
                    case BARCODE:
                        salesOrderPackage.setBarcode(cursor.getString(i));
                        break;
                }
            }
            salesOrderPackages.add(salesOrderPackage);
            cursor.moveToNext();
        }
        return salesOrderPackages;
    }
}
