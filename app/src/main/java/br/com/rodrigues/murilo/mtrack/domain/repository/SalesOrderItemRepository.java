package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrderItem;
import br.com.rodrigues.murilo.mtrack.infra.SQLiteHelper;
import br.com.rodrigues.murilo.mtrack.infra.service.ProductService;
import br.com.rodrigues.murilo.mtrack.infra.service.SalesOrderService;

public class SalesOrderItemRepository {
    // Name in DataBase
    public static final String TABLE = "PEDIDO_VENDA_ITEM";
    public static final String IDSALESORDERITEM = "ID_ITEMPEDIVEND";
    public static final String IDSALESORDER = "ID_PEDIVEND";
    public static final String IDPRODUCT = "ID_MATEEMBA";
    public static final String QUANTITY = "NR_EMBAEXPE";
    private static final String[] ALLCOLUMNS = {IDSALESORDERITEM, IDSALESORDER, IDPRODUCT, QUANTITY};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public SalesOrderItemRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<SalesOrderItem> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, IDSALESORDERITEM);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public List<SalesOrderItem> findByOrder(int idSalesOrder){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                                           IDSALESORDER + " = ?", new String[]{String.valueOf(idSalesOrder)},
                                           null, null, IDSALESORDER);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public SalesOrderItem findById(int idSalesOrderItem) {

        database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    IDSALESORDERITEM + " = ?", new String[]{String.valueOf(idSalesOrderItem)},
                    null, null, IDSALESORDERITEM);

            List<SalesOrderItem> salesOrderItems = toList(cursor);
            return salesOrderItems.isEmpty() ? null : salesOrderItems.get(0);
        } finally {
            database.close();
        }
    }

    public boolean insert(SalesOrderItem salesOrderItem){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(IDSALESORDERITEM, salesOrderItem.getIdSalesOrderItem());
            values.put(IDSALESORDER, salesOrderItem.getSalesOrder().getIdSalesOrder());
            values.put(IDPRODUCT, salesOrderItem.getProduct().getIdProduct());
            values.put(QUANTITY, salesOrderItem.getQuantity());

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

    public boolean deleteByOrder(SalesOrder salesOrder) {
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
            database.execSQL("DELETE FROM " + TABLE +
                             " WHERE " + IDSALESORDER + " IN " +
                                     " (SELECT " + SalesOrderRepository.IDSALESORDER +
                                     "    FROM " + SalesOrderRepository.TABLE +
                                     "   WHERE " + SalesOrderRepository.DELIVERED + " = 1");
        } finally {
            database.close();
        }
        return true;
    }

    // Read cursor and create list
    private List<SalesOrderItem> toList(Cursor cursor) {
        List<SalesOrderItem> salesOrderItems = new ArrayList<SalesOrderItem>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SalesOrderItem salesOrderItem = new SalesOrderItem();

            for(int i = 0; i < cursor.getColumnCount(); i++){
                switch (cursor.getColumnName(i)){
                    case IDSALESORDERITEM:
                        salesOrderItem.setIdSalesOrderItem(cursor.getInt(i));
                        break;
                    case IDSALESORDER:
                        salesOrderItem.setSalesOrder(SalesOrderService.findById(this.context, cursor.getInt(i)));
                        break;
                    case IDPRODUCT:
                        salesOrderItem.setProduct(ProductService.findById(this.context, cursor.getInt(i)));
                        break;
                    case QUANTITY:
                        salesOrderItem.setQuantity(cursor.getInt(i));
                        break;
                }
            }
            salesOrderItems.add(salesOrderItem);
            cursor.moveToNext();
        }
        return salesOrderItems;
    }

}
