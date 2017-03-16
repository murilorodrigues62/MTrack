package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.SalesOrder;
import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;

public class OrderRepository {
    // Name in DataBase
    public static final String PEDIDO_VENDA = "PEDIDO_VENDA";
    public static final String ID_PEDIVEND = "ID_PEDIVEND";
    public static final String ID_CARGEXPE = "ID_CARGEXPE";
    public static final String ID_CLIENTE = "ID_CLIENTE";
    public static final String NM_CLIENTE = "NM_CLIENTE";
    public static final String ID_ITEMPEDIVEND = "ID_ITEMPEDIVEND";
    public static final String ID_MATEEMBA = "ID_MATEEMBA";
    public static final String ID_PRODMATEEMBA = "ID_PRODMATEEMBA";
    public static final String NM_PRODMATEEMBA = "NM_PRODMATEEMBA";
    public static final String NR_EMBAEXPE = "NR_EMBAEXPE";
    public static final String FL_DELIVERED = "FL_DELIVERED";

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public OrderRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<SalesOrder> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(PEDIDO_VENDA, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE}, null, null, null, null, ID_PEDIVEND);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public SalesOrder findById(long id){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(PEDIDO_VENDA, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE},
                    ID_PEDIVEND + " = ?", new String[]{String.valueOf(id)}, null, null, ID_PEDIVEND);

            List<SalesOrder> salesOrders = toList(cursor);
            return salesOrders.isEmpty() ? null : salesOrders.get(0);
        } finally {
            database.close();
        }
    }

    // TODO: 15/03/17 Terminar implementação, ver como agrupar por mais de uma coluna
    /*
    public List<SalesOrder> findAllGroupOrder(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(PEDIDO_VENDA, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE}, null, null, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE}, null, ID_PEDIVEND);
            return toList(cursor);
        } finally {
            database.close();
        }
    }
*/
    public boolean insert(SalesOrder salesOrder){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(ID_PEDIVEND, salesOrder.getIdSalesOrder());
            values.put(ID_CARGEXPE, salesOrder.getIdDelivery());
            values.put(NM_CLIENTE, salesOrder.getCustomerName());

            database.insert(PEDIDO_VENDA, null, values);
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

            // get attributes
            salesOrder.setIdSalesOrder(cursor.getInt(0));
            salesOrder.setIdDelivery(cursor.getString(1));
            salesOrder.setCustomerName(cursor.getString(2));

            salesOrders.add(salesOrder);
            cursor.moveToNext();
        }
        return salesOrders;
    }
}
