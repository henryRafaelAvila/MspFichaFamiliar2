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
    public String[] getAllNacionalidad() {
        String selectQuery = "SELECT descripcion FROM cocina";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){
          //  do{
                String descripcion = cursor.getString(0);
                spinnerContent.add(descripcion);
         //   }while(cursor.moveToNext());
        }


        String[] allSpinner = new String[spinnerContent.size()];
        allSpinner = spinnerContent.toArray(allSpinner);
        return  allSpinner;
    }
 public List<Nacionalidad> getALLNacionalidad() {

        List<Nacionalidad> contactList = new ArrayList<Nacionalidad>();
        String selectQuery = "SELECT id_nac,cod_nac, descripcion FROM nacionalidad";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
            while (cursor.moveToNext()){
                Nacionalidad nac=new Nacionalidad();
                nac.id_nac=cursor.getInt(0);
                nac.cod_nac=cursor.getInt(1);
                nac.descripcion=cursor.getString(2);
                contactList.add(nac);
            }

        return contactList;
    }
}
