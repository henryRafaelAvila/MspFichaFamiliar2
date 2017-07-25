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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import z9.msp.gob.persistencia.entity.ActividadTrab;
import z9.msp.gob.persistencia.entity.AdministracionZonal;
import z9.msp.gob.persistencia.entity.Canton;
import z9.msp.gob.persistencia.entity.CausaMortalidad;
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
import z9.msp.gob.persistencia.entity.Mortalidad;
import z9.msp.gob.persistencia.entity.Nacionalidad;
import z9.msp.gob.persistencia.entity.Nacionalidade;
import z9.msp.gob.persistencia.entity.NivelInstruccion;
import z9.msp.gob.persistencia.entity.Parameters;
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
import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;
import z9.msp.gob.persistencia.utils.SM;
import z9.msp.gob.persistencia.utils.Utilitarios;

/**
 * Created by henry on 3/26/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 14;
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
        /*ENABLE FOREGIN KEY*/
        db.execSQL("PRAGMA foreign_keys = ON");//ON - OFF
        for (TABLES tables : TABLES.values()) {
            System.out.println(tables.getQuery());
            db.execSQL(tables.getQuery());

        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("********UPDATE DATA BASE*************");
        System.out.println("*******************************************");
        for (TABLES tables : TABLES.values()) {
            System.out.println("DROP TABLE IF EXISTS " + tables.getTablaName());
            db.execSQL("DROP TABLE IF EXISTS " + tables.getTablaName());
        }
        onCreate(db);
    }

    public ContentValues setValuesFormulario(String codigo, String id_mat_pis, String id_mat_par, String id_pro_agudd, String id_est_tech, String id_rec_agu, String id_cond_ocup,
                                             String id_tra_agu, String id_via_acc, String id_comb_coc, String id_tip_trans, String id_est_pis, String id_eli_bas, String id_eli_agu, String id_tip_viv, String id_ubi_let, String id_unid_oper, String id_mat_tec,
                                             String fecha_visita, String tiem_viv_meses, String tiemp_viv_anios, String entr_telf, String entr_cell, String pers_ref_telf, String pers_ref_cell, String num_cuar, String num_dorm, String contamina_suel_desc,
                                             String contamina_air_desc, String viol_fami, String dest_fami, String prob_gra_fam, String psico_soc, String aisla, String sin_escol, String nin_noescola, String alcoh, String droga, String contamina_agu_desc,
                                             String intradomi, String vec_trans, String anim_viv, String coc_inhog, String sedazo, String mosquit, String plaguicida, String aepi, String abandono, String ir_uni_sal, String calle1, String calle2, String telefono, String celular, String Edificio, String Manzana, String coordenadas, String responsable, String localidad, String institucion, String tiempo_transporte) {
        ContentValues values = new ContentValues();
        values.put("codigo", codigo);
        values.put("id_mat_pis", id_mat_pis);
        values.put("id_mat_par", id_mat_par);
        values.put("id_pro_agudd", id_pro_agudd);
        values.put("id_est_tech", id_est_tech);
        values.put("id_rec_agu", id_rec_agu);
        values.put("id_cond_ocup", id_cond_ocup);
        values.put("id_tra_agu", id_tra_agu);
        values.put("id_via_acc", id_via_acc);
        values.put("id_comb_coc", id_comb_coc);
        values.put("id_tip_trans", id_tip_trans);
        values.put("id_est_pis", id_est_pis);
        values.put("id_eli_bas", id_eli_bas);
        values.put("id_eli_agu", id_eli_agu);
        values.put("id_tip_viv", id_tip_viv);
        values.put("id_ubi_let", id_ubi_let);
        values.put("id_unid_oper", id_unid_oper);
        values.put("id_mat_tec", id_mat_tec);
        values.put("fecha_visita", fecha_visita);
        values.put("tiem_viv_meses", tiem_viv_meses);
        values.put("tiemp_viv_anios", tiemp_viv_anios);
        values.put("entr_telf", entr_telf);
        values.put("entr_cell", entr_cell);
        values.put("pers_ref_telf", pers_ref_telf);
        values.put("pers_ref_cell", pers_ref_cell);
        values.put("num_cuar", num_cuar);
        values.put("num_dorm", num_dorm);
        values.put("contamina_suel_desc", contamina_suel_desc);
        values.put("contamina_air_desc", contamina_air_desc);
        values.put("viol_fami", viol_fami);
        values.put("dest_fami", dest_fami);
        values.put("prob_gra_fam", prob_gra_fam);
        values.put("psico_soc", psico_soc);
        values.put("aisla", aisla);
        values.put("sin_escol", sin_escol);
        values.put("nin_noescola", nin_noescola);
        values.put("alcoh", alcoh);
        values.put("droga", droga);
        values.put("contamina_agu_desc", contamina_agu_desc);
        values.put("intradomi", intradomi);
        values.put("vec_trans", vec_trans);
        values.put("anim_viv", anim_viv);
        values.put("coc_inhog", coc_inhog);
        values.put("sedazo", sedazo);
        values.put("mosquit", mosquit);
        values.put("plaguicida", plaguicida);
        values.put("aepi", aepi);
        values.put("abandono", abandono);
        values.put("ir_uni_sal", ir_uni_sal);
        values.put("calle1", calle1);
        values.put("calle2", calle2);
        values.put("telefono", telefono);
        values.put("celular", celular);
        values.put("Edificio", Edificio);
        values.put("Manzana", Manzana);
        values.put("coordenadas", coordenadas);
        values.put("responsable", responsable);
        values.put("localidad", localidad);
        values.put("institucion", institucion);
        values.put("tiempo_transporte", tiempo_transporte);
        return values;
/*long id=db.insert("formulario", null, values);
        return String.valueOf(id);*/
    }

    public Cursor getUnidadDatos(String tabla, String id) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
            db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM " + tabla + " WHERE _id=?";
            cursor = db.rawQuery(selectQuery, new String[]{id});
             return cursor;
    }

    public String existeObjectByCedulaAndFormID(String cedula,String formulaId,TABLES tables) {
        String id=null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + tables.getTablaName() + " WHERE cedula=? and id_formulario=?";
        cursor = db.rawQuery(selectQuery, new String[]{cedula,formulaId});
        if(cursor.moveToNext()){
            id=cursor.getString(cursor.getColumnIndex("_id"));
        }
        if(cursor!=null){
            closeCursor(cursor);
        }
        return id;
    }

    public Cursor getAllDiscrimiator(Parameters parameters) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
         String selectQuery = "SELECT * FROM " + parameters.getTABLE().getTablaName() + " " +
                    "WHERE " + parameters.getCOLS().getColsName() + "=?";
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, new String[]{parameters.getValueString()});

        return cursor;
    }

    public Cursor getAllById(TABLES tables, String id) {
        return getUnidadDatos(tables.getTablaName(), id);
    }

    public List<Formulario> getFormulariosList() {
        Cursor cursor = getAllGeneric(TABLES.FORMULARIO.getTablaName());
        List<Formulario> formularioList = new ArrayList<>();

        String nombre, descripcion, zona;
        int cont = 1;
        int id = 1;
        int codigo=-1;
        if (cursor.moveToNext()) {
            do {
                nombre = "Formulario " + cont;
                codigo=cursor.getInt(cursor.getColumnIndex("codigo"));
                if(codigo>0){
                descripcion = SM.codigo+codigo;
                }else{
                    descripcion = SM.codigo+id;
                }
                zona = "Zona 9";
                id = cursor.getInt(cursor.getColumnIndex("_id"));
                formularioList.add(new Formulario(nombre, descripcion, zona, id));
                cont++;
            } while (cursor.moveToNext());
        }
        closeCursor(cursor);
        return formularioList;
    }

    public Personas getPersonaById(String id) {
        Cursor cursor = getAllById(TABLES.PERSONAS, id);
        Personas personas = null;
        int idPue=-1;
        if (cursor.moveToNext()) {
            personas = new Personas();
            personas.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
            personas.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
            personas.setNumCedula(cursor.getString(cursor.getColumnIndex("cedula")));
            personas.setDetSegPrivado(cursor.getString(cursor.getColumnIndex("det_seg_privado")));
            Date fechaDiadnostic=Utilitarios.stringToDate(cursor.getString(cursor.getColumnIndex("fecha_diag")));
            personas.setFechaDiag(fechaDiadnostic);
            Date fechaNac=Utilitarios.stringToDate(cursor.getString(cursor.getColumnIndex("fecha_nac")));
            personas.setFechaNac(fechaNac);

            personas.setSeguroPriv(cursor.getInt(cursor.getColumnIndex("seguro_priv")));
            personas.setIdActTrab(cursor.getInt(cursor.getColumnIndex("id_act_trab")));
            personas.setIdClafDiag(cursor.getInt(cursor.getColumnIndex("id_claf_diag")));
            personas.setIdEstCiv(cursor.getInt(cursor.getColumnIndex("id_est_civ")));
            personas.setIdEtn(cursor.getInt(cursor.getColumnIndex("id_etn")));
            personas.setIdFormulario(cursor.getInt(cursor.getColumnIndex("id_formulario")));
            personas.setIdNac(cursor.getInt(cursor.getColumnIndex("id_nac")));
            if(cursor.getInt(cursor.getColumnIndex("id_nacs"))>0) {
                personas.setIdNacs(cursor.getInt(cursor.getColumnIndex("id_nacs")));
            }
            personas.setIdNivInst(cursor.getInt(cursor.getColumnIndex("id_niv_inst")));
            personas.setIdParJh(cursor.getInt(cursor.getColumnIndex("id_par_jh")));
            personas.setIdPersona(cursor.getInt(cursor.getColumnIndex("_id")));
            idPue=cursor.getInt(cursor.getColumnIndex("id_pue"));
            if(idPue>0) {
                personas.setIdPue(cursor.getInt(cursor.getColumnIndex("id_pue")));
            }
            personas.setIdSegPub(cursor.getInt(cursor.getColumnIndex("id_seg_pub")));
            personas.setSexo(cursor.getInt(cursor.getColumnIndex("sexo")));


        }
        closeCursor(cursor);
        return personas;
    }

    public Cursor getAllGeneric(String tableName) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
            String selectQuery = "SELECT * FROM " + tableName;
            db = this.getWritableDatabase();
            cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public String getWs(WS wsService) {
        String url = null;
        Cursor cursor = getAllGeneric(TABLES.CONFIG_SERVER.getTablaName());
        if (cursor.moveToNext()) {
            String ip=cursor.getString(cursor.getColumnIndex("ip"));
            String puerto=cursor.getString(cursor.getColumnIndex("puerto"));
            String appName=cursor.getString(cursor.getColumnIndex("servicio_cat"));//nombre podria causar confusion, hace referencia al nombre de la aplcacion del servidor
            url = "http://" + ip + ":" + puerto+"/"+appName +wsService.getUrlPath();
        }
        return url;
    }

    public String getWsUser() {
        String user = null;
        Cursor cursor = getAllGeneric(TABLES.CONFIG_SERVER.getTablaName());
        if (cursor.moveToNext()) {
            user = cursor.getString(cursor.getColumnIndex(CLS_DISCR.USER_WS.getColsName()));
        }
        closeCursor(cursor);
        return user;
    }

    public String getWsPassword() {
        String password = null;
        Cursor cursor = getAllGeneric(TABLES.CONFIG_SERVER.getTablaName());
        if (cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex(CLS_DISCR.PASSWORD_WS.getColsName()));
        }
        closeCursor(cursor);
        return password;
    }

    public void insert(TABLES table, Object o) {

        switch (table) {
            case NACIONALIDAD:
                insertNacionalidad(table, (Nacionalidad) o);
                break;
            case NACIONALIDADES:
                insertNacionalidades(table, (Nacionalidade) o);
                break;
            case ETNIA:
                insertEtnia(table, (Etnia) o);
                break;
            case SEGPUBLICO:
                insertSeguroPublico(table, (SeguroPublico) o);
                break;
            case PARENT_JE_HO:
                insertParentescoJefeHogar(table, (ParentescoJefeHogar) o);
                break;
            case ESTADO_CIVIL:
                insertEstadoCivil(table, (EstadoCivil) o);
                break;
            case NIVEL_INSTRS:
                insertNivelInstruccion(table, (NivelInstruccion) o);
                break;
            case ACTIVIADAD_TRABAJO:
                insertActividadTrabajo(table, (ActividadTrab) o);
                break;
            case PUEBLOS:
                insertPueblos(table, (Pueblo) o);
                break;
            case CONDICION_OCUPACION:
                insertCondicionOcupacion(table, (CondicionOcupacion) o);
                break;
            case TIPO_VIVIENDA:
                insertTipoVivienda(table, (TipoVivienda) o);
                break;
            case TIPO_TRANSPORTE:
                insertTipoTransporte(table, (TipoTransp) o);
                break;
            case MATERIAL_TECHO:
                insertMaterialTecho(table, (MaterialTecho) o);
                break;
            case MATERIAL_PISO:
                insertMaterialPiso(table, (MaterialPiso) o);
                break;
            case MATERIAL_PARED:
                insertMaterialPared(table, (MaterialPared) o);
                break;
            case ESTADO_TECHO:
                insertEstadoTecho(table, (EstadoTecho) o);
                break;
            case ESTADO_PISO:
                insertEstadoPiso(table, (EstadoPiso) o);
                break;
            case COMBUSTIBLE_COCINA:
                insertCombustibleCocinar(table, (CombustibleCocinar) o);
                break;
            case PROCEDENCIA_AGUA:
                insertProcedenciaAgua(table, (ProcedenciaAgua) o);
                break;
            case RECIBE_AGUA:
                insertRecibeAgua(table, (RecibeAgua) o);
                break;
            case TRATAMIENTO_AGUA:
                insertTratamientoAgua(table, (TratamientoAgua) o);
                break;
            case ELIMINAR_AGUA_SERVIDAS:
                insertEliminarAguaSer(table, (EliminarAguaSer) o);
                break;
            case UBICACION_LETRETE:
                insertUbicacionLetrete(table, (UbicacionLetrete) o);
                break;
            case ELIMINAR_BASURA:
                insertEliminarBasura(table, (EliminarBasura) o);
                break;
            case CLASF_DIAGNOS:
                insertClasifDiagnostico(table, (ClasifDiagnostico) o);
                break;
            case VIAS_ACCESO:
                insertViasAcceso(table, (ViasAcceso) o);
                break;
            case CANTON:
                insertCanton(table, (Canton) o);
                break;
            case PROVINCIA:
                insertProvincia(table, (Provincia) o);
                break;
            case PARROQUIA:
                insertParroquia(table, (Parroquia) o);
                break;
            case UNIDAD_OPERATIVA:
                insertUnidadOperativa(table, (UnidadOperativa) o);
                break;
            case DISTRITO:
                insertDistrito(table, (Distrito) o);
                break;
            case ADMISNISTRACION_ZONAL:
                insertAdministracionZonal(table, (AdministracionZonal) o);
                break;
            case CAUSA_MOTALIDAD:
                insertCausaMortalidad(table,(CausaMortalidad)o);
                break;

        }
    }

    private void insertAdministracionZonal(TABLES table, AdministracionZonal obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdAdmin());
        values.put("cod", obj.getCod());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertDistrito(TABLES table, Distrito obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdDistrito());
        values.put("cod_distrito", obj.getCodDistrito());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
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
        executeCreateQuery(values, table);
    }

    private void insertParroquia(TABLES table, Parroquia obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdParroquia());
        values.put("id_canton", obj.getId_canton());
        values.put("tipo", obj.getTipo());
        values.put("id_distrito", obj.getId_distrito());
        values.put("cod_parr", obj.getCodParr());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertProvincia(TABLES table, Provincia obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdProvincia());
        values.put("cod_prov", obj.getCodProv());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertCanton(TABLES table, Canton obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCanton());
        values.put("id_provincia", obj.getProvincia_id());
        values.put("cod_cant", obj.getCodCant());
        values.put("id_admin", obj.getId_admin());

        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertClasifDiagnostico(TABLES table, ClasifDiagnostico obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdClafDiag());
        values.put("cod_claf_diag", obj.getCodClafDiag());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEliminarBasura(TABLES table, EliminarBasura obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEliBas());
        values.put("cod_eli_bas", obj.getCodEliBas());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertViasAcceso(TABLES table, ViasAcceso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdViaAcc());
        values.put("cod_via_acc", obj.getCodViaAcc());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertUbicacionLetrete(TABLES table, UbicacionLetrete obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdUbiLet());
        values.put("cod_ubi_let", obj.getCodUbiLet());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEliminarAguaSer(TABLES table, EliminarAguaSer obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdElimAguSer());
        values.put("cod_agua_ser", obj.getCodAguaSer());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertTratamientoAgua(TABLES table, TratamientoAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTraAgu());
        values.put("cod_tra_agu", obj.getCodTraAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertRecibeAgua(TABLES table, RecibeAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdRecAgu());
        values.put("cod_rec_agu", obj.getCodRecAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertProcedenciaAgua(TABLES table, ProcedenciaAgua obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdProAgudd());
        values.put("cod_pro_agu", obj.getCodProAgu());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertCombustibleCocinar(TABLES table, CombustibleCocinar obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCombCoc());
        values.put("cod_coc", obj.getCodCoc());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEstadoPiso(TABLES table, EstadoPiso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEstPis());
        values.put("cod_est_pis", obj.getCodEstPis());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEstadoTecho(TABLES table, EstadoTecho obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdEstTech());
        values.put("cod_est_tech", obj.getCodEstTech());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertMaterialPared(TABLES table, MaterialPared obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatPar());
        values.put("cod_mat_par", obj.getCodMatPar());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertMaterialPiso(TABLES table, MaterialPiso obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatPis());
        values.put("cod_mat_pis", obj.getCodMatPis());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertMaterialTecho(TABLES table, MaterialTecho obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdMatTec());
        values.put("cod_mat_tec", obj.getCodMatTec());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertTipoTransporte(TABLES table, TipoTransp obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTipTrans());
        values.put("cod_tip_trans", obj.getCodTipTrans());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertTipoVivienda(TABLES table, TipoVivienda obj) {

        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdTipVivId());
        values.put("cod_tip_viv", obj.getCodTipViv());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);

    }

    private void insertCondicionOcupacion(TABLES table, CondicionOcupacion obj) {

        ContentValues values = new ContentValues();
        values.put("_id", obj.getIdCondOcup());
        values.put("cod_ocup", obj.getCodOcup());
        values.put("descripcion", obj.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertActividadTrabajo(TABLES table, ActividadTrab actividadTrab) {

        ContentValues values = new ContentValues();
        values.put("_id", actividadTrab.getIdActTrab());
        values.put("cod_act_trab", actividadTrab.getCodActTrab());
        values.put("descripcion", actividadTrab.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertNivelInstruccion(TABLES table, NivelInstruccion nivelInstruccion) {

        ContentValues values = new ContentValues();
        values.put("_id", nivelInstruccion.getIdNivInst());
        values.put("cod_niv_inst", nivelInstruccion.getCodNivInst());
        values.put("descripcion", nivelInstruccion.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertParentescoJefeHogar(TABLES table, ParentescoJefeHogar parentescoJefeHogar) {
        ContentValues values = new ContentValues();
        values.put("_id", parentescoJefeHogar.getIdParJh());
        values.put("cod_par_jh", parentescoJefeHogar.getCodParJh());
        values.put("descripcion", parentescoJefeHogar.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertSeguroPublico(TABLES table, SeguroPublico seguroPublico) {
        ContentValues values = new ContentValues();
        values.put("_id", seguroPublico.getIdSegPub());
        values.put("cod_seg_pub", seguroPublico.getCodSegPub());
        values.put("descripcion", seguroPublico.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEtnia(TABLES table, Etnia etnia) {
        ContentValues values = new ContentValues();
        values.put("_id", etnia.getIdEtn());
        values.put("cod_etn", etnia.getCodEtn());
        values.put("descripcion", etnia.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertNacionalidades(TABLES table, Nacionalidade nacionalidades) {
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidades.getIdNacs());
        values.put("cod_nacs", nacionalidades.getCodNacs());
        values.put("descripcion", nacionalidades.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertNacionalidad(TABLES table, Nacionalidad nacionalidad) {
        ContentValues values = new ContentValues();
        values.put("_id", nacionalidad.getId_nac());
        values.put("cod_nac", nacionalidad.getCod_nac());
        values.put("descripcion", nacionalidad.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertEstadoCivil(TABLES table, EstadoCivil estadoCivil) {

        ContentValues values = new ContentValues();
        values.put("_id", estadoCivil.getIdEstCiv());
        values.put("cod_est_civ", estadoCivil.getCodEstCiv());
        values.put("descripcion", estadoCivil.getDescripcion());
        executeCreateQuery(values, table);
    }

    private void insertPueblos(TABLES table, Pueblo pueblo) {
        ContentValues values = new ContentValues();
        values.put("_id", pueblo.getIdPue());
        values.put("cod_pue", pueblo.getCodPue());
        values.put("descripcion", pueblo.getDescripcion());
        executeCreateQuery(values, table);
    }
    private void insertCausaMortalidad(TABLES table, CausaMortalidad causaMortalidad) {
        ContentValues values = new ContentValues();
        values.put("_id", causaMortalidad.getIdCauMor());
        values.put("cod_cau_mor", causaMortalidad.getCodCauMor());
        values.put("descripcion", causaMortalidad.getDescripcion());
        executeCreateQuery(values, table);
    }


    public boolean executeCreateQuery(ContentValues values, TABLES table) {
        boolean createSuccessful;
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful = db.insert(table.getTablaName(), null, values) > 0;
        Log.i("Query", "insertando... " + table + ":" + createSuccessful);

        return createSuccessful;
    }

    public String insertWithParam(TABLES table, ContentValues values) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(table.getTablaName(), null, values);

        return String.valueOf(id);
    }

    public int updateById(TABLES table, String id, ContentValues values) {
        int numRows;
        SQLiteDatabase db = this.getWritableDatabase();
        numRows = db.update(table.getTablaName(), values, "_id=" + id, null);

        return numRows;
    }

    public void deleteData() {
        SQLiteDatabase database = this.getWritableDatabase();
        int delete;
        for (TABLES tables : TABLES.values()) {
            if(tables!=TABLES.CONFIG_SERVER) {
                delete = database.delete(tables.getTablaName(), null, null);
                System.out.println("Eliminado datos de " + tables + ": " + delete);
            }

        }

    }

    public void deleteData(TABLES tables) {
        SQLiteDatabase database = this.getWritableDatabase();
        int delete;
        delete = database.delete(tables.getTablaName(), null, null);
        System.out.println("Eliminado datos de " + tables + ": " + delete);

    }


    public Formulario exportData(String formularioId) {
        Formulario formulario = getFormuarioByID(formularioId);
        if (formulario != null) {
            formulario.setPersonas(getPersonas(formularioId));
            formulario.setMortalidads(getListMortalidad(formularioId));
        }

        return formulario;
    }

    public Formulario getFormuarioByID(String formularioId) {
        Cursor cursor = getAllById(TABLES.FORMULARIO, formularioId);
        Formulario formulario = null;
        int id_fk=0;
        if (cursor.moveToNext()) {
            formulario = new Formulario();
            formulario.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            id_fk=cursor.getInt(cursor.getColumnIndex("id_mat_pis"));

            if(id_fk>0)
            formulario.setIdMatPis(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_mat_par"));
            if(id_fk>0)
            formulario.setIdMatPar(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_pro_agudd"));
            if(id_fk>0)
            formulario.setIdProAgudd(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_est_tech"));
            if(id_fk>0)
            formulario.setIdEstTech(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_rec_agu"));
            if(id_fk>0)
            formulario.setIdRecAgu(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_cond_ocup"));
            if(id_fk>0)
            formulario.setIdCondOcup(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_tra_agu"));
            if(id_fk>0)
            formulario.setIdTraAgu(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_via_acc"));
            if(id_fk>0)
            formulario.setIdViaAcc(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_comb_coc"));
            if(id_fk>0)
            formulario.setIdCombCoc(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_tip_trans"));
            if(id_fk>0)
            formulario.setIdTipTrans(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_est_pis"));
            if(id_fk>0)
            formulario.setIdEstPis(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_eli_bas"));
            if(id_fk>0)
            formulario.setIdEliBas(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_eli_agu"));
            if(id_fk>0)
            formulario.setIdEliAgu(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_tip_viv"));
            if(id_fk>0)
            formulario.setIdTipViv(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_ubi_let"));
            if(id_fk>0)
            formulario.setIdUbiLet(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_unid_oper"));
            if(id_fk>0)
            formulario.setIdUnidOper(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("id_mat_tec"));
            if(id_fk>0)
            formulario.setIdMatTec(id_fk);
            Date fechaVisita=Utilitarios.stringToDate(cursor.getString(cursor.getColumnIndex("fecha_visita")));
            formulario.setFechaVisita(fechaVisita);
            formulario.setTiemVivMeses(cursor.getInt(cursor.getColumnIndex("tiem_viv_meses")));
            formulario.setTiempVivAnios(cursor.getInt(cursor.getColumnIndex("tiemp_viv_anios")));
            formulario.setEntrCell(cursor.getString(cursor.getColumnIndex("entr_cell")));
            formulario.setEntrTelf(cursor.getString(cursor.getColumnIndex("entr_telf")));
            formulario.setPersRefCell(cursor.getString(cursor.getColumnIndex("pers_ref_cell")));
            formulario.setPersRefTelf(cursor.getString(cursor.getColumnIndex("pers_ref_telf")));

            id_fk=cursor.getInt(cursor.getColumnIndex("viol_fami"));
            if(id_fk>0)
            formulario.setViolFami(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("dest_fami"));
            if(id_fk>0)
            formulario.setDestFami(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("prob_gra_fam"));
            if(id_fk>0)
            formulario.setProbGraFam(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("psico_soc"));
            if(id_fk>0)
            formulario.setPsicoSoc(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("aisla"));
            if(id_fk>0)
            formulario.setAisla(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("sin_escol"));
            if(id_fk>0)
            formulario.setSinEscol(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("nin_noescola"));
            if(id_fk>0)
            formulario.setNinNoescola(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("alcoh"));
            if(id_fk>0)
            formulario.setAlcoh(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("droga"));
            if(id_fk>0)
            formulario.setDroga(id_fk);
            formulario.setContaminaAguDesc(cursor.getString(cursor.getColumnIndex("contamina_agu_desc")));
            formulario.setContaminaAirDesc(cursor.getString(cursor.getColumnIndex("contamina_air_desc")));
            formulario.setContaminaSuelDesc(cursor.getString(cursor.getColumnIndex("contamina_suel_desc")));

            id_fk=cursor.getInt(cursor.getColumnIndex("intradomi"));
            if(id_fk>0)
            formulario.setIntradomi(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("vec_trans"));
            if(id_fk>0)
            formulario.setVecTrans(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("anim_viv"));
            if(id_fk>0)
            formulario.setAnimViv(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("coc_inhog"));
            if(id_fk>0)
            formulario.setCocInhog(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("sedazo"));
            if(id_fk>0)
            formulario.setSedazo(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("mosquit"));
            if(id_fk>0)
            formulario.setMosquit(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("plaguicida"));
            if(id_fk>0)
            formulario.setPlaguicida(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("aepi"));
            if(id_fk>0)
            formulario.setAepi(id_fk);

            id_fk=cursor.getInt(cursor.getColumnIndex("abandono"));
            if(id_fk>0)
            formulario.setAbandono(id_fk);

            formulario.setIrUniSal(cursor.getInt(cursor.getColumnIndex("ir_uni_sal")));
            formulario.setCalle1(cursor.getString(cursor.getColumnIndex("calle1")));
            formulario.setCalle2(cursor.getString(cursor.getColumnIndex("calle2")));
            formulario.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
            formulario.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
            formulario.setEdificio(cursor.getString(cursor.getColumnIndex("Edificio")));
            formulario.setManzana(cursor.getInt(cursor.getColumnIndex("Manzana")));
            formulario.setCoordenadas(cursor.getString(cursor.getColumnIndex("coordenadas")));
            formulario.setResponsable(cursor.getString(cursor.getColumnIndex("responsable")));
            formulario.setLocalidad(cursor.getString(cursor.getColumnIndex("localidad")));
            formulario.setNumCuar(cursor.getString(cursor.getColumnIndex("num_cuar")));
            formulario.setTiempoTransporte(cursor.getInt(cursor.getColumnIndex("tiempo_transporte")));

        }
        closeCursor(cursor);
        return formulario;
    }

    private List<Personas> getPersonas(String formularioId) {
        Cursor cursor =getAllDiscrimiator(new Parameters(TABLES.PERSONAS,CLS_DISCR.FORMULARIO_ID,formularioId));
        List<Personas> personasList = null;
        if (cursor.moveToNext()) {
            personasList = new ArrayList<Personas>();
            do {
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                Personas personas = getPersonaById(id);
                personasList.add(personas);

            } while (cursor.moveToNext());

        }
        closeCursor(cursor);
        return personasList;
    }

    public void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    private List<Mortalidad> getListMortalidad(String formularioId) {
        Cursor cursor =getAllDiscrimiator(new Parameters(TABLES.MORTALIDAD,CLS_DISCR.FORMULARIO_ID,formularioId));
        List<Mortalidad> mortalidadList = null;
        if (cursor.moveToNext()) {
            mortalidadList = new ArrayList<Mortalidad>();
            do {
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                Mortalidad obj = getMortalidadById(id);
                mortalidadList.add(obj);

            } while (cursor.moveToNext());

        }
        closeCursor(cursor);
        return mortalidadList;
    }

    public Mortalidad getMortalidadById(String id) {
        Cursor cursor = getAllById(TABLES.MORTALIDAD, id);
        Mortalidad mortalidad = null;
        if (cursor.moveToNext()) {
            mortalidad = new Mortalidad();
            mortalidad.setIdMortalida(cursor.getInt(cursor.getColumnIndex("_id")));
            mortalidad.setIdCauMor(cursor.getInt(cursor.getColumnIndex("id_cau_mor")));
            mortalidad.setNombres(cursor.getString(cursor.getColumnIndex("nombres")));
            mortalidad.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
            mortalidad.setCausa(cursor.getString(cursor.getColumnIndex("causa")));
            mortalidad.setNumCedula(cursor.getString(cursor.getColumnIndex("cedula")));
            mortalidad.setEdad(cursor.getInt(cursor.getColumnIndex("edad")));
            Date fechaMuerte=Utilitarios.stringToDate(cursor.getString(cursor.getColumnIndex("fecha_muerte")));
            mortalidad.setFechaMuerte(fechaMuerte);
            mortalidad.setIdParJh(cursor.getInt(cursor.getColumnIndex("id_par_jh")));
            mortalidad.setSexo(cursor.getInt(cursor.getColumnIndex("sexo")));
            mortalidad.setIdFormulario(cursor.getInt(cursor.getColumnIndex("id_formulario")));


        }
        closeCursor(cursor);
        return mortalidad;
    }

    public int deleteByKey(TABLES tables, CLS_DISCR clsDiscr, String value) {
        SQLiteDatabase database = this.getWritableDatabase();
        int delete = -1;
        delete = database.delete(tables.getTablaName(), clsDiscr.getColsName() + "=" + value, null);

        return delete;
    }

    private int deletePersonasByFormId(String formularioId) {
        return deleteByKey(TABLES.PERSONAS, CLS_DISCR.FORMULARIO_ID, formularioId);
    }

    private int deleteMortalidadByFormId(String formularioId) {
        return deleteByKey(TABLES.MORTALIDAD, CLS_DISCR.FORMULARIO_ID, formularioId);
    }

    public int deleteFormularioById(String formularioId) {
        deletePersonasByFormId(formularioId);
        deleteMortalidadByFormId(formularioId);
        return deleteByKey(TABLES.FORMULARIO, CLS_DISCR.ID, formularioId);
    }
public  int insertFormularioByWs(Formulario formulario){
String idFormulario=insertFormularios(formulario);
    for(Personas persona:formulario.getPersonas()){
        persona.setIdFormulario(Integer.parseInt(idFormulario));
        insertPersona(persona);
    }
    for(Mortalidad mortalidad:formulario.getMortalidads()){
        mortalidad.setIdFormulario(Integer.parseInt(idFormulario));
        insertMortalidad(mortalidad);
    }
    return 0;
};
    public String insertFormularios(Formulario formulario) {
        ContentValues values = new ContentValues();
        values.put("codigo", formulario.getCodigo());
        values.put("id_mat_pis", formulario.getIdMatPis());
        values.put("id_mat_par", formulario.getIdMatPar());
        values.put("id_pro_agudd", formulario.getIdProAgudd());
        values.put("id_est_tech", formulario.getIdEstTech());
        values.put("id_rec_agu", formulario.getIdRecAgu());
        values.put("id_cond_ocup", formulario.getIdCondOcup());
        values.put("id_tra_agu", formulario.getIdTraAgu());
        values.put("id_via_acc", formulario.getIdViaAcc());
        values.put("id_comb_coc", formulario.getIdCombCoc());
        values.put("id_tip_trans", formulario.getIdTipTrans());
        values.put("id_est_pis", formulario.getIdEstPis());
        values.put("id_eli_bas", formulario.getIdEliBas());
        values.put("id_eli_agu", formulario.getIdEliAgu());
        values.put("id_tip_viv", formulario.getIdTipViv());
        values.put("id_ubi_let", formulario.getIdUbiLet());
        values.put("id_unid_oper", formulario.getIdUnidOper());
        values.put("id_mat_tec", formulario.getIdMatTec());
        values.put("fecha_visita", Utilitarios.dateToString(formulario.getFechaVisita()));
        values.put("tiem_viv_meses", formulario.getTiemVivMeses());
        values.put("tiemp_viv_anios", formulario.getTiempVivAnios());
        values.put("entr_telf", formulario.getEntrTelf());
        values.put("entr_cell", formulario.getEntrCell());
        values.put("pers_ref_telf", formulario.getPersRefTelf());
        values.put("pers_ref_cell", formulario.getPersRefCell());
        values.put("num_cuar", formulario.getNumCuar());
        values.put("num_dorm", formulario.getNumDorm());
        values.put("contamina_suel_desc", formulario.getContaminaSuelDesc());
        values.put("contamina_air_desc", formulario.getContaminaAirDesc());
        values.put("viol_fami", formulario.getViolFami());
        values.put("dest_fami", formulario.getDestFami());
        values.put("prob_gra_fam", formulario.getProbGraFam());
        values.put("psico_soc", formulario.getPsicoSoc());
        values.put("aisla", formulario.getAisla());
        values.put("sin_escol", formulario.getSinEscol());
        values.put("nin_noescola", formulario.getNinNoescola());
        values.put("alcoh", formulario.getAlcoh());
        values.put("droga", formulario.getDroga());
        values.put("contamina_agu_desc", formulario.getContaminaAguDesc());
        values.put("intradomi", formulario.getIntradomi());
        values.put("vec_trans", formulario.getVecTrans());
        values.put("anim_viv", formulario.getAnimViv());
        values.put("coc_inhog", formulario.getCocInhog());
        values.put("sedazo", formulario.getSedazo());
        values.put("mosquit", formulario.getMosquit());
        values.put("plaguicida", formulario.getPlaguicida());
        values.put("aepi", formulario.getAepi());
        values.put("abandono", formulario.getAbandono());
        values.put("ir_uni_sal", formulario.getIrUniSal());
        values.put("calle1", formulario.getCalle1());
        values.put("calle2", formulario.getCalle2());
        values.put("telefono", formulario.getTelefono());
        values.put("celular", formulario.getCelular());
        values.put("Edificio", formulario.getEdificio());
        values.put("Manzana", formulario.getManzana());
        values.put("coordenadas", formulario.getCoordenadas());
        values.put("responsable", formulario.getResponsable());
        values.put("localidad", formulario.getLocalidad());
        values.put("institucion", formulario.getInstitucion());
        values.put("tiempo_transporte", formulario.getTiempoTransporte());
        values=Utilitarios.decodeContentValues(values);

        return insertWithParam(TABLES.FORMULARIO,values);
    }
    public String insertPersona(Personas persona){
        ContentValues values=new ContentValues();
        values.put(CLS_DISCR.FORMULARIO_ID.getColsName(),persona.getIdFormulario());
        values.put("cedula",persona.getNumCedula());
        values.put("apellidos",persona.getApellidos());
        values.put("nombres",persona.getNombres());
        values.put("id_par_jh",persona.getIdParJh());
        values.put("fecha_nac",Utilitarios.dateToString(persona.getFechaNac()));
        values.put("sexo",persona.getSexo());
        values.put("id_est_civ",persona.getIdEstCiv());
        values.put("id_act_trab",persona.getIdActTrab());
        values.put("id_niv_inst",persona.getIdNivInst());
        values.put("id_etn",persona.getIdEtn());
        values.put("id_nacs",persona.getIdNacs());
        values.put("id_pue",persona.getIdPue());
        values.put("id_nac",persona.getIdNac());
        values.put("id_seg_pub",persona.getSeguroPriv());
        values.put("id_claf_diag",persona.getIdClafDiag());
        final Calendar c = Calendar.getInstance();
        String fechaVisiata=c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
        values.put("fecha_diag",fechaVisiata);//si
        values.put("seguro_priv",persona.getSeguroPriv());//si
        values.put("det_seg_privado",persona.getDetSegPrivado());
        values=Utilitarios.decodeContentValues(values);
        return insertWithParam(TABLES.PERSONAS,values);
    }
    public String insertMortalidad(Mortalidad mortalidad){
        ContentValues values=new ContentValues();
        values.put(CLS_DISCR.FORMULARIO_ID.getColsName(),mortalidad.getIdFormulario());
        values.put("sexo",mortalidad.getSexo());
        values.put("apellidos",mortalidad.getApellidos());
        values.put("nombres",mortalidad.getNombres());
        values.put("cedula",mortalidad.getCedula());
        values.put("edad",mortalidad.getEdad());
        values.put("causa",mortalidad.getCausa());
        values.put("id_cau_mor",mortalidad.getIdCauMor());
        values.put("id_par_jh",mortalidad.getIdParJh());
        values.put("fecha_muerte",Utilitarios.dateToString(mortalidad.getFechaMuerte()));

        values=Utilitarios.decodeContentValues(values);
        return insertWithParam(TABLES.MORTALIDAD,values);
    }
}
