package example.tese.practicas.practica01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Base de Datos
    static final String BD = "COLEGIO";

    // Nombre de Tabla
    public static final String TABLA = "alumno";

    // Columnas
    public static final String _ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String CARRERA = "carrera";
    public static final String CURP = "curp";

    // database version
    static final int BD_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLA + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE + " TEXT NOT NULL, " + CARRERA + " TEXT, " + CURP + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, BD, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
        onCreate(db);
    }
}
