package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.infra.service.CustomerService;
import br.com.rodrigues.murilo.mtrack.infra.SQLiteHelper;

public class SalesOrderRepository {
    // Name in DataBase
    public static final String TABLE = "PEDIDO_VENDA";
    public static final String IDSALESORDER = "ID_PEDIVEND";
    public static final String IDDELIVERY = "ID_CARGEXPE";
    public static final String IDCUSTOMER = "ID_CLIENTE";
    public static final String DELIVERED = "FL_DELIVERED";
    private static final String[] ALLCOLUMNS = {IDDELIVERY, IDSALESORDER, IDCUSTOMER, DELIVERED};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public SalesOrderRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<SalesOrder> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, IDSALESORDER);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public SalesOrder findById(int idSalesOrder){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                                           IDSALESORDER + " = ?", new String[]{String.valueOf(idSalesOrder)},
                                           null, null, IDSALESORDER);

            List<SalesOrder> salesOrders = toList(cursor);
            return salesOrders.isEmpty() ? null : salesOrders.get(0);
        } finally {
            database.close();
        }
    }

    public boolean insert(SalesOrder salesOrder){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(IDSALESORDER, salesOrder.getIdSalesOrder());
            values.put(IDDELIVERY, salesOrder.getIdDelivery());
            values.put(IDCUSTOMER, salesOrder.getCustomer().getIdCustomer());
            values.put(DELIVERED, salesOrder.isDelivered());

            database.insert(TABLE, null, values);
        } finally {
            database.close();
        }
        return true;
    }

    public boolean deleteAll() {
        database = dbHelper.getWritableDatabase();
        try {
            database.execSQL("DELETE FROM " + TABLE);
        } finally {
            database.close();
        }
        return true;
    }

    public boolean delete(SalesOrder salesOrder) {
        database = dbHelper.getWritableDatabase();
        try {
            database.execSQL("DELETE FROM " + TABLE + " WHERE " + IDSALESORDER + " = " + String.valueOf(salesOrder.getIdSalesOrder()));
        } finally {
            database.close();
        }
        return true;
    }

    public boolean deleteFinished() {
        database = dbHelper.getWritableDatabase();
        try {
            database.execSQL("DELETE FROM " + TABLE + " WHERE " + DELIVERED + " = 1 ");
        } finally {
            database.close();
        }
        return true;
    }

    // Read cursor and create list
    private List<SalesOrder> toList(Cursor cursor) {
        List<SalesOrder> salesOrders = new ArrayList<SalesOrder>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SalesOrder salesOrder = new SalesOrder();

            for(int i = 0; i < cursor.getColumnCount(); i++){
                switch (cursor.getColumnName(i)){
                    case IDSALESORDER:
                        salesOrder.setIdSalesOrder(cursor.getInt(i));
                        break;
                    case IDDELIVERY:
                        salesOrder.setIdDelivery(cursor.getInt(i));
                        break;
                    case IDCUSTOMER:
                        salesOrder.setCustomer(CustomerService.findById(this.context, cursor.getInt(i)));
                        break;
                    case DELIVERED:
                        salesOrder.setDelivered((cursor.getInt(i) == 1));
                        break;
                }
            }

            salesOrders.add(salesOrder);
            cursor.moveToNext();
        }
        return salesOrders;
    }

    public boolean finishOrder(int idSalesOrder, boolean isDelivered) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DELIVERED, isDelivered ? 1 : 0);

        String where = IDSALESORDER + " =? ";

        String[] whereArgs = {String.valueOf(idSalesOrder)};

        try {
            int count = database.update(TABLE, values, where, whereArgs);
            return count == 1;
        } finally {
            database.close();
        }
    }
}
