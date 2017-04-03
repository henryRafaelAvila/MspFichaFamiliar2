package z9.msp.gob.persistencia;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


import z9.msp.gob.persistencia.entity.Nacionalidad;

/**
 * Created by henry on 3/26/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ficha_familiar_msp";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("********CREATE TABLES DATABASE*************");
        System.out.println("*******************************************");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getAllCocina() {
        String selectQuery = "SELECT * FROM cocina";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getOcupacion() {
        String selectQuery = "SELECT * FROM cocina";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }
    public Cursor getAllTransporte() {
        String selectQuery = "SELECT * FROM cocina";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }
}
