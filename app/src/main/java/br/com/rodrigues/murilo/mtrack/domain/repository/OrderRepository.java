package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;
import br.com.rodrigues.murilo.mtrack.domain.model.Order;

public class OrderRepository {
    // Name in DataBase
    public static final String PEDIDO_VENDA = "PEDIDO_VENDA";
    public static final String ID_PEDIVEND = "ID_PEDIVEND";
    public static final String ID_CARGEXPE = "ID_CARGEXPE";
    public static final String NM_CLIENTE = "NM_CLIENTE";

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public OrderRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<Order> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(PEDIDO_VENDA, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE}, null, null, null, null, ID_PEDIVEND);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public Order findById(long id){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(PEDIDO_VENDA, new String[]{ID_PEDIVEND, ID_CARGEXPE, NM_CLIENTE},
                    ID_PEDIVEND + " = ?", new String[]{String.valueOf(id)}, null, null, ID_PEDIVEND);

            List<Order> orders = toList(cursor);
            return orders.isEmpty() ? null : orders.get(0);
        } finally {
            database.close();
        }
    }

    public boolean insert(Order order){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(ID_PEDIVEND, order.getId());
            values.put(ID_CARGEXPE, order.getCharge());
            values.put(NM_CLIENTE, order.getClient());

            database.insert(PEDIDO_VENDA, null, values);
        } finally {
            database.close();
        }
        return true;
    }

    // Read cursor and create list
    private List<Order> toList(Cursor cursor) {
        List<Order> orders = new ArrayList<Order>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Order order = new Order();

            // get attributes
            order.setId(cursor.getInt(0));
            order.setCharge(cursor.getString(1));
            order.setClient(cursor.getString(2));

            orders.add(order);
            cursor.moveToNext();
        }
        return orders;
    }
}
