package example.tese.practicas.practica01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String nombre, String carrera, String curp) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NOMBRE, nombre);
        contentValue.put(DatabaseHelper.CARRERA, carrera);
        contentValue.put(DatabaseHelper.CURP, curp);
        database.insert(DatabaseHelper.TABLA, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NOMBRE, DatabaseHelper.CARRERA, DatabaseHelper.CURP };
        Cursor cursor = database.query(DatabaseHelper.TABLA, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String nombre, String carrera, String curp) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NOMBRE, nombre);
        contentValues.put(DatabaseHelper.CARRERA, carrera);
        contentValues.put(DatabaseHelper.CURP, curp);
        int i = database.update(DatabaseHelper.TABLA, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLA, DatabaseHelper._ID + "=" + _id, null);
    }

}
