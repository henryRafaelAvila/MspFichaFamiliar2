package z9.msp.gob.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;
import android.provider.ContactsContract;
import android.util.Log;


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
import z9.msp.gob.persistencia.entity.AdministracionZonal;
import z9.msp.gob.persistencia.entity.Canton;
import z9.msp.gob.persistencia.entity.ClasifDiagnostico;
import z9.msp.gob.persistencia.entity.CombustibleCocinar;
import z9.msp.gob.persistencia.entity.CondicionOcupacion;
import z9.msp.gob.persistencia.entity.Distrito;
import z9.msp.gob.persistencia.entity.EliminarAguaSer;
import z9.msp.gob.persistencia.entity.EliminarBasura;
import z9.msp.gob.persistencia.entity.EstadoCivil;
import z9.msp.gob.persistencia.entity.EstadoPiso;
import z9.msp.gob.persistencia.entity.EstadoTecho;
import z9.msp.gob.persistencia.entity.Etnia;
import z9.msp.gob.persistencia.entity.Formulario;
import z9.msp.gob.persistencia.entity.MaterialPared;
import z9.msp.gob.persistencia.entity.MaterialPiso;
import z9.msp.gob.persistencia.entity.MaterialTecho;
import z9.msp.gob.persistencia.entity.Nacionalidad;
import z9.msp.gob.persistencia.entity.Nacionalidade;
import z9.msp.gob.persistencia.entity.NivelInstruccion;
import z9.msp.gob.persistencia.entity.ParentescoJefeHogar;
import z9.msp.gob.persistencia.entity.Parroquia;
import z9.msp.gob.persistencia.entity.Personas;
import z9.msp.gob.persistencia.entity.ProcedenciaAgua;
import z9.msp.gob.persistencia.entity.Provincia;
import z9.msp.gob.persistencia.entity.Pueblo;
import z9.msp.gob.persistencia.entity.RecibeAgua;
import z9.msp.gob.persistencia.entity.SeguroPublico;
import z9.msp.gob.persistencia.entity.TipoTransp;
import z9.msp.gob.persistencia.entity.TipoVivienda;
import z9.msp.gob.persistencia.entity.TratamientoAgua;
import z9.msp.gob.persistencia.entity.UbicacionLetrete;
import z9.msp.gob.persistencia.entity.UnidadOperativa;
import z9.msp.gob.persistencia.entity.ViasAcceso;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;

/**
 * Created by henry on 3/26/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION =11 ;
    private static final String DATABASE_NAME = "ficha_familiar_msp";
    private Context context;
    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("********CREATE TABLES DATABASE*************");
        System.out.println("*******************************************");
        /*ENABLE FOREGIN KEY*/
        db.execSQL("PRAGMA foreign_keys = ON");//ON - OFF
        for (TABLES tables:TABLES.values()){
            System.out.println(tables.getQuery());
            db.execSQL(tables.getQuery());

        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("********UPDATE DATA BASE*************");
        System.out.println("*******************************************");
        for (TABLES tables:TABLES.values()){
            System.out.println("DROP TABLE IF EXISTS " + tables.getTablaName());
            db.execSQL("DROP TABLE IF EXISTS " + tables.getTablaName());
        }
        onCreate(db);
    }


     public Cursor getUnidadDatos(String tabla,String id) {
        String selectQuery = "SELECT * FROM "+tabla+" WHERE _id=?";
        Cursor cursor = db.rawQuery(selectQuery,  new String[] { id});
        return  cursor;
    }

    public Cursor getAllGeneric(String tableName) {
        String selectQuery = "SELECT * FROM "+tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return  cursor;
    }
    public String getWs(WS columnName) {
        String url=null;
        Cursor cursor =getAllGeneric(TABLES.CONFIG_SERVER.getTablaName());
        if (cursor.moveToFirst()) {
            int colIndex=cursor.getColumnIndex(columnName.getColumnName());
            url="http://"+cursor.getString(1)+":"+cursor.getInt(2)+"/"+cursor.getString(colIndex)+"?usuario="+cursor.getString(6)+"&clave="+cursor.getString(7);
        }
        return url;
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
            case CONDICION_OCUPACION:
                insertCondicionOcupacion(table,(CondicionOcupacion) o);
                break;
            case TIPO_VIVIENDA:
                insertTipoVivienda(table,(TipoVivienda) o);
                break;
            case TIPO_TRANSPORTE:
                insertTipoTransporte(table,(TipoTransp) o);
                break;
            case MATERIAL_TECHO:
                insertMaterialTecho(table,(MaterialTecho) o);
                break;
            case MATERIAL_PISO:
                insertMaterialPiso(table,(MaterialPiso) o);
                break;
            case MATERIAL_PARED:
                insertMaterialPared(table,(MaterialPared) o);
                break;
            case ESTADO_TECHO:
                insertEstadoTecho(table,(EstadoTecho) o);
                break;
            case ESTADO_PISO:
                insertEstadoPiso(table,(EstadoPiso) o);
                break;
            case COMBUSTIBLE_COCINA:
                insertCombustibleCocinar(table,(CombustibleCocinar) o);
                break;
            case PROCEDENCIA_AGUA:
                insertProcedenciaAgua(table,(ProcedenciaAgua) o);
                break;
            case RECIBE_AGUA:
                insertRecibeAgua(table,(RecibeAgua) o);
                break;
            case TRATAMIENTO_AGUA:
                insertTratamientoAgua(table,(TratamientoAgua) o);
                break;
            case ELIMINAR_AGUA_SERVIDAS:
                insertEliminarAguaSer(table,(EliminarAguaSer) o);
                break;
            case UBICACION_LETRETE:
                insertUbicacionLetrete(table,(UbicacionLetrete) o);
                break;
            case ELIMINAR_BASURA:
                insertEliminarBasura(table,(EliminarBasura) o);
                break;
            case CLASF_DIAGNOS:
                insertClasifDiagnostico(table,(ClasifDiagnostico) o);
                break;
            case VIAS_ACCESO:
                insertViasAcceso(table,(ViasAcceso) o);
                break;
            case CANTON:
                insertCanton(table,(Canton) o);
                break;
            case PROVINCIA:
                insertProvincia(table,(Provincia) o);
                break;
            case PARROQUIA:
                insertParroquia(table,(Parroquia) o);
                break;
            case UNIDAD_OPERATIVA:
                insertUnidadOperativa(table,(UnidadOperativa) o);
                break;
            case DISTRITO:
                insertDistrito(table,(Distrito) o);
                break;
            case ADMISNISTRACION_ZONAL:
                insertAdministracionZonal(table,(AdministracionZonal) o);
                break;

        }
    }
    private void insertAdministracionZonal(TABLES table, AdministracionZonal obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdAdmin());
        values.put("cod", obj.getCod());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertDistrito(TABLES table, Distrito obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdDistrito());
        values.put("cod_distrito", obj.getCodDistrito());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertUnidadOperativa(TABLES table, UnidadOperativa obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdUnidOper());
        values.put("id_parroquia", obj.getId_parroquia());
        values.put("cod_uni_oper", obj.getCodUniOper());
        values.put("nombre_oficial", obj.getNombreOficial());
        values.put("nombre_comun", obj.getNombreComun());
        values.put("direccion", obj.getDireccion());
        values.put("telf", obj.getTelf());
        executeCreateQuery(values,table);
    }
    private void insertParroquia(TABLES table, Parroquia obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdParroquia());
        values.put("id_canton", obj.getId_canton());
        values.put("tipo", obj.getTipo());
        values.put("id_distrito", obj.getId_distrito());
        values.put("cod_parr", obj.getCodParr());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertProvincia(TABLES table, Provincia obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdProvincia());
        values.put("cod_prov", obj.getCodProv());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertCanton(TABLES table, Canton obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCanton());
        values.put("id_provincia", obj.getProvincia_id());
        values.put("cod_cant", obj.getCodCant());
        values.put("id_admin", obj.getId_admin());

        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertClasifDiagnostico(TABLES table, ClasifDiagnostico obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdClafDiag());
        values.put("cod_claf_diag", obj.getCodClafDiag());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEliminarBasura(TABLES table, EliminarBasura obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEliBas());
        values.put("cod_eli_bas", obj.getCodEliBas());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertViasAcceso(TABLES table, ViasAcceso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdViaAcc());
        values.put("cod_via_acc", obj.getCodViaAcc());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertUbicacionLetrete(TABLES table, UbicacionLetrete obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdUbiLet());
        values.put("cod_ubi_let", obj.getCodUbiLet());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEliminarAguaSer(TABLES table, EliminarAguaSer obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdElimAguSer());
        values.put("cod_agua_ser", obj.getCodAguaSer());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertTratamientoAgua(TABLES table, TratamientoAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTraAgu());
        values.put("cod_tra_agu", obj.getCodTraAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertRecibeAgua(TABLES table, RecibeAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdRecAgu());
        values.put("cod_rec_agu", obj.getCodRecAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertProcedenciaAgua(TABLES table, ProcedenciaAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdProAgudd());
        values.put("cod_pro_agu", obj.getCodProAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertCombustibleCocinar(TABLES table, CombustibleCocinar obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCombCoc());
        values.put("cod_coc", obj.getCodCoc());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEstadoPiso(TABLES table, EstadoPiso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEstPis());
        values.put("cod_est_pis", obj.getCodEstPis());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEstadoTecho(TABLES table, EstadoTecho obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEstTech());
        values.put("cod_est_tech", obj.getCodEstTech());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertMaterialPared(TABLES table, MaterialPared obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatPar());
        values.put("cod_mat_par", obj.getCodMatPar());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertMaterialPiso(TABLES table, MaterialPiso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatPis());
        values.put("cod_mat_pis", obj.getCodMatPis());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertMaterialTecho(TABLES table, MaterialTecho obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatTec());
        values.put("cod_mat_tec", obj.getCodMatTec());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }

    private void insertTipoTransporte(TABLES table, TipoTransp obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTipTrans());
        values.put("cod_tip_trans", obj.getCodTipTrans());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }

    private void insertTipoVivienda(TABLES table, TipoVivienda obj) {

        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTipVivId());
        values.put("cod_tip_viv", obj.getCodTipViv());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);

    }
    private void insertCondicionOcupacion(TABLES table, CondicionOcupacion obj) {

        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCondOcup());
        values.put("cod_ocup", obj.getCodOcup());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertActividadTrabajo(TABLES table, ActividadTrab actividadTrab) {

        ContentValues values = new ContentValues();
        values.put("_id", actividadTrab.getIdActTrab());
        values.put("cod_act_trab", actividadTrab.getCodActTrab());
        values.put("descripcion", actividadTrab.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertNivelInstruccion(TABLES table, NivelInstruccion nivelInstruccion) {

        ContentValues values = new ContentValues();
        values.put("_id", nivelInstruccion.getIdNivInst());
        values.put("cod_niv_inst", nivelInstruccion.getCodNivInst());
        values.put("descripcion", nivelInstruccion.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertParentescoJefeHogar(TABLES table, ParentescoJefeHogar parentescoJefeHogar) {
        ContentValues values = new ContentValues();
        values.put("_id", parentescoJefeHogar.getIdParJh());
        values.put("cod_par_jh", parentescoJefeHogar.getCodParJh());
        values.put("descripcion", parentescoJefeHogar.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertSeguroPublico(TABLES table, SeguroPublico seguroPublico) {
        ContentValues values = new ContentValues();
        values.put("_id", seguroPublico.getIdSegPub());
        values.put("cod_seg_pub", seguroPublico.getCodSegPub());
        values.put("descripcion", seguroPublico.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEtnia(TABLES table, Etnia etnia) {
        ContentValues values = new ContentValues();
        values.put("_id", etnia.getIdEtn());
        values.put("cod_etn", etnia.getCodEtn());
        values.put("descripcion", etnia.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertNacionalidades(TABLES table, Nacionalidade nacionalidades) {
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidades.getIdNacs());
        values.put("cod_nacs", nacionalidades.getCodNacs());
        values.put("descripcion", nacionalidades.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertNacionalidad(TABLES table,Nacionalidad nacionalidad) {
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidad.getId_nac());
        values.put("cod_nac", nacionalidad.getCod_nac());
        values.put("descripcion", nacionalidad.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertEstadoCivil(TABLES table,EstadoCivil estadoCivil) {

        ContentValues values = new ContentValues();
        values.put("_id", estadoCivil.getIdEstCiv());
        values.put("cod_est_civ", estadoCivil.getCodEstCiv());
        values.put("descripcion", estadoCivil.getDescripcion());
        executeCreateQuery(values,table);
    }
    private void insertPueblos(TABLES table,Pueblo pueblo) {
        ContentValues values = new ContentValues();
        values.put("_id", pueblo.getIdPue());
        values.put("cod_pue", pueblo.getCodPue());
        values.put("descripcion", pueblo.getDescripcion());
        executeCreateQuery(values,table);
    }
    public boolean executeCreateQuery(ContentValues values,TABLES table){
        boolean createSuccessful;
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        Log.i("Query","insertando... "+table+":"+createSuccessful);
        db.close();
        return createSuccessful;
    }
    public int updateById(TABLES table,int id,ContentValues values){
        int numRows;
        SQLiteDatabase db = this.getWritableDatabase();
        numRows= db.update(table.getTablaName(), values,"_id="+id, null);
        db.close();
        return  numRows;
    }
    public void deleteData() {
        SQLiteDatabase database=this.getWritableDatabase();
        int delete;
        for(TABLES tables:TABLES.values()){
            delete=database.delete(tables.getTablaName(),null,null);
            System.out.println("Eliminado datos de "+tables+": "+delete);
        }
        database.close();
    }
    public void deleteData(TABLES tables) {
        SQLiteDatabase database=this.getWritableDatabase();
        int delete;
        delete=database.delete(tables.getTablaName(),null,null);
        System.out.println("Eliminado datos de "+tables+": "+delete);
         database.close();
    }
    public boolean insertFormularios(Formulario formulario){
        boolean insertado=false;
        //TODO implemnetae insercion de formulario, creado para descarga de formularios desde el servidor by token
        return insertado;
    }
    public List<Formulario> exportData(){
        Cursor cursor = getAllGeneric(TABLES.FORMULARIO.getTablaName());
        if (cursor.moveToFirst()) {
            do {
                String formularioId=cursor.getString(cursor.getColumnIndex("_id"));
                Formulario formulario=new Formulario();
                formulario.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                formulario.setPersonas(getPersonas(formularioId));

            }while (cursor.moveToNext());

        }
        return null;
    }
    private List<Personas> getPersonas(String formularioId){
        Cursor cursor = getUnidadDatos(TABLES.PERSONAS.getTablaName(),formularioId);
        List<Personas> personasList=null;
        if (cursor.moveToFirst()) {
            personasList=new ArrayList<Personas>();
            do {
                Personas personas=new Personas();
                personas.setNombres(cursor.getString(cursor.getColumnIndex("nombre")));
                personasList.add(personas);

            }while (cursor.moveToNext());

        }
        return personasList;
    }

}
