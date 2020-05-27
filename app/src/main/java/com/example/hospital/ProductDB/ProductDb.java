package com.example.hospital.ProductDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hospital.Register.Login;

import java.util.ArrayList;
import java.util.List;

public class ProductDb extends SQLiteOpenHelper {
    public static String DB_NAME = "productss";
    private static int DB_VERSION = 1;
    public static final String TABLE_NAME = "customers";
    public  static  final String PRODUCT_ID = "product_id";
    public static final String SERIAL_NO = "serial_no";
    public static final String P_NAME = "product_name";
    public static final String P_PRICE = "product_price";
    public static final String P_SIZE = "product_size";
    public static final String P_COLOR = "product_color";
    public static final String P_QUANTITY = "product_quantity";
    public static final String P_CODE = "product_code";
    public static final String SIZE_ARRAY = "size_array";
    public static final String COLOR_ARRAY = "color_array";
    public static final String TOTAL = "total";
    public static final String IMAGE = "image";
    public static String strSeparator = "__,__";
    public ProductDb(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + SERIAL_NO +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_NAME +
                " VARCHAR, " + PRODUCT_ID +
                " VARCHAR, "+ P_CODE+
                " VARCHAR, "+ P_COLOR +
                " VARCHAR, " + P_SIZE +
                " VARCHAR, "+ P_QUANTITY +
                " VARCHAR, " + P_PRICE +
                " VARCHAR, "+ SIZE_ARRAY +
                " VARCHAR, " + COLOR_ARRAY +
                " VARCHAR, " + TOTAL +
                " VARCHAR, " + IMAGE +
                " VARCHAR " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public void addProducts(String productname,String productId,String color,String code,String quantity
                ,String size,String price,String sizeArray,String colorArray,String image ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String upperString = productname.substring(0,1).toUpperCase() + productname.substring(1);
        contentValues.put(P_NAME, upperString);
        contentValues.put(PRODUCT_ID,productId);
        contentValues.put(P_QUANTITY, quantity);
        contentValues.put(P_SIZE,size);
        contentValues.put(P_COLOR,color);
        contentValues.put(P_PRICE,price);
        contentValues.put(P_CODE,code);
        contentValues.put(SIZE_ARRAY,sizeArray);
        contentValues.put(COLOR_ARRAY,colorArray);
        contentValues.put(TOTAL,price);
        contentValues.put(IMAGE,image);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public List<proLocalArray> getAllProducts()
    {
        List<proLocalArray> contactList = new ArrayList<proLocalArray>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                    proLocalArray product = new proLocalArray();
                    product.setSerial(cursor.getString(0));
                    product.setName(cursor.getString(1));
                    product.setId(cursor.getString(2));
                    product.setCode(cursor.getString(3));
                    product.setColor(cursor.getString(4));
                    product.setSize(cursor.getString(5));
                    product.setQuantity(cursor.getString(6));
                    product.setPrice(cursor.getString(7));
                    product.setColorArray(convertStringToArray(cursor.getString(8)));
                    product.setSizeArray(convertStringToArray(cursor.getString(9)));
                    product.setTotalAmount(cursor.getString(10));
                    product.setImageURL(cursor.getString(11));
                    contactList.add(product);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
    public int updateProducts(String id, String  quantity,String color,String size,String total ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_QUANTITY, quantity);
        contentValues.put(P_COLOR,color);
        contentValues.put(P_SIZE,size);
        contentValues.put(TOTAL,total);
        return db.update(TABLE_NAME, contentValues, SERIAL_NO + " = ?",
                new String[]{String.valueOf(id)});
    }
    public Cursor getId(String  id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM customers WHERE serial_no = " + "'"+ id +"'";
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void deleteProduct(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SERIAL_NO + " = ?",
                new String[] {id});
        db.close();
    }
    public  void deleteTables()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql=  "DELETE FROM "+ TABLE_NAME ;
        db.execSQL(sql);
        db.close();

    }
}
