package mv.hospital.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListDB extends SQLiteOpenHelper {
    public static String DB_NAME = "listss";
    private static int DB_VERSION = 1;
    public static final String TABLE_NAME = "list";

    public  static  final String PRODUCT_ID = "id";
    public static final String SERIAL_NO = "number";
    public static final String P_NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String IMAGE = "image";


    public ListDB(Context context ) {

        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + SERIAL_NO +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_NAME +
                " VARCHAR, " + PRODUCT_ID +
                " VARCHAR, "+ AMOUNT +
                " VARCHAR, "+ IMAGE +
                " BLOB " +
                 ");";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addItems(String id,String name,String amount,String quan,byte[] image) {

    }

    public Cursor getId(String  id)
    {

        Cursor  cursor = null;

        return cursor;
    }
    public Cursor getIds(String  id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM list WHERE booth_id = " + "'"+ id +"'";
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public  void deleteTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql=  "DELETE FROM "+ TABLE_NAME ;
        db.execSQL(sql);
        db.close();
    }

}