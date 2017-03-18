package br.com.rodrigues.murilo.mtrack.domain.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigues.murilo.mtrack.domain.model.Product;
import br.com.rodrigues.murilo.mtrack.domain.util.SQLiteHelper;

public class ProductRepository {
    // Name in DataBase
    public static final String TABLE = "MATERIAL_EMBALAGEM";
    public static final String IDPRODUCT = "ID_MATEEMBA";
    public static final String PRODUCTCODE = "ID_PRODMATEEMBA";
    public static final String PRODUCTNAME = "NM_PRODMATEEMBA";
    private static final String[] ALLCOLUMNS = {IDPRODUCT, PRODUCTCODE, PRODUCTNAME};

    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ProductRepository(Context context) {
        this.context = context;
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<Product> findAll(){
        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS, null, null, null, null, IDPRODUCT);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public boolean insert(Product Product){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(IDPRODUCT, Product.getId());
            values.put(PRODUCTCODE, Product.getCode());
            values.put(PRODUCTNAME, Product.getName());

            database.insert(TABLE, null, values);
        } finally {
            database.close();
        }
        return true;
    }

    public Product findById(int idProduct){

        database=dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.query(TABLE, ALLCOLUMNS,
                    IDPRODUCT + " = ?", new String[]{String.valueOf(idProduct)}, null, null, null);

            List<Product> products = toList(cursor);
            return products.isEmpty() ? null : products.get(0);
        } finally {
            database.close();
        }
    }

    // Read cursor and create list
    private List<Product> toList(Cursor cursor) {
        List<Product> products = new ArrayList<Product>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = new Product();
            // get attributes
            product.setId(cursor.getInt(0));
            product.setCode(cursor.getString(1));
            product.setName(cursor.getString(2));
            products.add(product);
            cursor.moveToNext();
        }
        return products;
    }
}
