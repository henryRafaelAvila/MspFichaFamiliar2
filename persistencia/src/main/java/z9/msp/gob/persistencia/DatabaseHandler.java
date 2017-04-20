package z9.msp.gob.persistencia;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    private Context context;

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

    public Cursor getAllUnidad() {
        String selectQuery = "SELECT * FROM unidad_operativa";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllOcupacion() {
        String selectQuery = "SELECT * FROM condicion_ocupacion";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllTipoVivienda() {
        String selectQuery = "SELECT * FROM tipo_vivienda";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllAccesoVivienda() {
        String selectQuery = "SELECT * FROM vias_acceso";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllTipoTransporte() {
        String selectQuery = "SELECT * FROM tipo_transp";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllMaterialTecho() {
        String selectQuery = "SELECT * FROM material_techo";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllMaterialPiso() {
        String selectQuery = "SELECT * FROM material_piso";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllMaterialParedes() {
        String selectQuery = "SELECT * FROM material_pared";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllEstadoTecho() {
        String selectQuery = "SELECT * FROM estado_techo";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllEstadoPiso() {
        String selectQuery = "SELECT * FROM estado_piso";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllCocina() {
        String selectQuery = "SELECT * FROM combustible_cocinar";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllProvieneAgua() {
        String selectQuery = "SELECT * FROM procedencia_agua";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllRecibeAgua() {
        String selectQuery = "SELECT * FROM recibe_agua";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllTratamientoAgua() {
        String selectQuery = "SELECT * FROM tratamiento_agua";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllEliminaAguaServida() {
        String selectQuery = "SELECT * FROM eliminar_agua_ser";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllUbicacionRetrete() {
        String selectQuery = "SELECT * FROM ubicacion_letrete";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllBasura() {
        String selectQuery = "SELECT * FROM eliminar_basura";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }

    public Cursor getAllGeneric(String tableName) {
        String selectQuery = "SELECT * FROM "+tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> spinnerContent = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }



}
