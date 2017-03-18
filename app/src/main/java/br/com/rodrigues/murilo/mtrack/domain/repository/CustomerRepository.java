package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Customer;
import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;

public class CustomerRepository {
    // Name in DataBase
    public static final String TABLE = "CLIENTE";
    public static final String IDCUSTOMER = "ID_CLIENTE";
    public static final String CUSTOMERNAME = "NM_CLIENTE";
    private static final String[] ALLCOLUMNS = {IDCUSTOMER, CUSTOMERNAME};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public CustomerRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<Customer> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, CUSTOMERNAME);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public Customer findById(long idCustomer){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    IDCUSTOMER + " = ?", new String[]{String.valueOf(idCustomer)}, null, null, IDCUSTOMER);

            List<Customer> customers = toList(cursor);
            return customers.isEmpty() ? null : customers.get(0);
        } finally {
            database.close();
        }
    }

    // Read cursor and create list
    private List<Customer> toList(Cursor cursor) {
        List<Customer> Customers = new ArrayList<Customer>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Customer customer = new Customer();

            for(int i = 0; i < cursor.getColumnCount(); i++){
                switch (cursor.getColumnName(i)){
                    case IDCUSTOMER:
                        customer.setIdCustomer(cursor.getInt(i));
                        break;
                    case CUSTOMERNAME:
                        customer.setCustomerName(cursor.getString(i));
                        break;
                }
            }

            Customers.add(customer);
            cursor.moveToNext();
        }
        return Customers;
    }
}
