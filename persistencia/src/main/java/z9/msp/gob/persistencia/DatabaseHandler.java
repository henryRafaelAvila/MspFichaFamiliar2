package z9.msp.gob.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import z9.msp.gob.persistencia.entity.ActividadTrab;
import z9.msp.gob.persistencia.entity.EstadoCivil;
import z9.msp.gob.persistencia.entity.Etnia;
import z9.msp.gob.persistencia.entity.Nacionalidad;
import z9.msp.gob.persistencia.entity.Nacionalidade;
import z9.msp.gob.persistencia.entity.NivelInstruccion;
import z9.msp.gob.persistencia.entity.ParentescoJefeHogar;
import z9.msp.gob.persistencia.entity.Pueblo;
import z9.msp.gob.persistencia.entity.SeguroPublico;
import z9.msp.gob.persistencia.enums.TABLES;

/**
 * Created by henry on 3/26/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ficha_familiar_msp2";
    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("********CREATE TABLES DATABASE*************");
        System.out.println("*******************************************");
        for (TABLES tables:TABLES.values()){
            System.out.println(tables.getQuery());
            db.execSQL(tables.getQuery());

        }
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
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }
    public void insert(TABLES table,Object o) {

        switch (table){
            case NACIONALIDAD:
                insertNacionalidad(table,(Nacionalidad)o);
                break;
            case NACIONALIDADES:
                insertNacionalidades(table,(Nacionalidade)o);
                break;
            case ETNIA:
                insertEtnia(table,(Etnia) o);
                break;
            case SEGPUBLICO:
                insertSeguroPublico(table,(SeguroPublico) o);
                break;
            case PARENT_JE_HO:
                insertParentescoJefeHogar(table,(ParentescoJefeHogar) o);
                break;
            case ESTADO_CIVIL:
                insertEstadoCivil(table,(EstadoCivil) o);
                break;
            case NIVEL_INSTRS:
                insertNivelInstruccion(table,(NivelInstruccion) o);
                break;
            case ACTIVIADAD_TRABAJO:
                insertActividadTrabajo(table,(ActividadTrab) o);
                break;
            case PUEBLOS:
                insertPueblos(table,(Pueblo) o);
                break;

        }
    }
    public void insertActividadTrabajo(TABLES table, ActividadTrab actividadTrab) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", actividadTrab.getIdActTrab());
        values.put("cod_act_trab", actividadTrab.getCodActTrab());
        values.put("descripcion", actividadTrab.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertNivelInstruccion(TABLES table, NivelInstruccion nivelInstruccion) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", nivelInstruccion.getIdNivInst());
        values.put("cod_niv_inst", nivelInstruccion.getCodNivInst());
        values.put("descripcion", nivelInstruccion.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertParentescoJefeHogar(TABLES table, ParentescoJefeHogar parentescoJefeHogar) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", parentescoJefeHogar.getIdParJh());
        values.put("cod_par_jh", parentescoJefeHogar.getCodParJh());
        values.put("descripcion", parentescoJefeHogar.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertSeguroPublico(TABLES table, SeguroPublico seguroPublico) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", seguroPublico.getIdSegPub());
        values.put("cod_seg_pub", seguroPublico.getCodSegPub());
        values.put("descripcion", seguroPublico.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertEtnia(TABLES table, Etnia etnia) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", etnia.getIdEtn());
        values.put("cod_etn", etnia.getCodEtn());
        values.put("descripcion", etnia.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertNacionalidades(TABLES table, Nacionalidade nacionalidades) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidades.getIdNacs());
        values.put("cod_nacs", nacionalidades.getCodNacs());
        values.put("descripcion", nacionalidades.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertNacionalidad(TABLES table,Nacionalidad nacionalidad) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidad.getId_nac());
        values.put("cod_nac", nacionalidad.getCod_nac());
        values.put("descripcion", nacionalidad.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertEstadoCivil(TABLES table,EstadoCivil estadoCivil) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", estadoCivil.getIdEstCiv());
        values.put("cod_est_civ", estadoCivil.getCodEstCiv());
        values.put("descripcion", estadoCivil.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void insertPueblos(TABLES table,Pueblo pueblo) {
        boolean createSuccessful;
        ContentValues values = new ContentValues();
        values.put("_id", pueblo.getIdPue());
        values.put("cod_pue", pueblo.getCodPue());
        values.put("descripcion", pueblo.getDescripcion());
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        System.out.println("Dato insetado: "+createSuccessful);
        db.close();
    }
    public void deleteData() {
        SQLiteDatabase database=this.getWritableDatabase();
        int delete;
        for(TABLES tables:TABLES.values()){
            delete=database.delete(tables.getTablaName(),null,null);
            System.out.println("Dato rliminao: "+delete);
        }
        database.close();
    }
    public String getValueUnique(Cursor cursor,String columnName){
        String value=null;
        if (cursor.moveToFirst()){
                value = cursor.getString(cursor.getColumnIndex(columnName));

        }
        return value;
    }




}
