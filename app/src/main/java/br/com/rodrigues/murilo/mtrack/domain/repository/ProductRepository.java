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
    public static final String MATERIAL_EMBALAGEM = "MATERIAL_EMBALAGEM";
    public static final String ID_MATEEMBA = "ID_MATEEMBA";
    public static final String ID_PRODMATEEMBA = "ID_PRODMATEEMBA";
    public static final String NM_PRODMATEEMBA = "NM_PRODMATEEMBA";

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
            Cursor cursor = database.query(MATERIAL_EMBALAGEM, new String[]{ID_MATEEMBA, ID_PRODMATEEMBA, NM_PRODMATEEMBA}, null, null, null, null, ID_MATEEMBA);
            return toList(cursor);
        } finally {
            database.close();
        }
    }

    public boolean insert(Product Product){
        database=dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(ID_MATEEMBA, Product.getId());
            values.put(ID_PRODMATEEMBA, Product.getCode());
            values.put(NM_PRODMATEEMBA, Product.getName());

            database.insert(MATERIAL_EMBALAGEM, null, values);
        } finally {
            database.close();
        }
        return true;
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
