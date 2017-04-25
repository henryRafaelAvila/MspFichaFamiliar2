package z9.msp.gob.persistencia.enums;

import z9.msp.gob.persistencia.entity.ActividadTrab;
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
import z9.msp.gob.persistencia.entity.MaterialPared;
import z9.msp.gob.persistencia.entity.MaterialPiso;
import z9.msp.gob.persistencia.entity.MaterialTecho;
import z9.msp.gob.persistencia.entity.Nacionalidad;
import z9.msp.gob.persistencia.entity.Nacionalidade;
import z9.msp.gob.persistencia.entity.NivelInstruccion;
import z9.msp.gob.persistencia.entity.ParentescoJefeHogar;
import z9.msp.gob.persistencia.entity.Parroquia;
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

/**
 * Created by henry on 4/20/2017.
 */

public enum TABLES {
    PROVINCIA("provincia",Provincia.class,"CREATE TABLE provincia (_id INTEGER PRIMARY KEY  NOT NULL , cod_prov VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    CANTON("canton",Canton.class,"CREATE TABLE canton (_id INTEGER PRIMARY KEY  NOT NULL ,id_prov Integer NOT NULL  DEFAULT (null) ,cod_cant VARCHAR NOT NULL ,descripcion VARCHAR NOT NULL )"),
    PARROQUIA("parroquia",Parroquia.class,"CREATE TABLE parroquia (_id INTEGER PRIMARY KEY  NOT NULL , id_canton INTEGER NOT NULL , id_distrito INTEGER NOT NULL , cod_parr VARCHAR NOT NULL , tipo VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    DISTRITO("distrito",Distrito.class,"CREATE TABLE distrito (_id INTEGER PRIMARY KEY  NOT NULL , cod_distrito VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    UNIDAD_OPERATIVA("unidad_operativa",UnidadOperativa.class,"CREATE TABLE unidad_operativa (_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , id_parroquia INTEGER NOT NULL , cod_uni_oper VARCHAR NOT NULL , nombre_oficial VARCHAR NOT NULL , nombre_comun VARCHAR NOT NULL , direccion VARCHAR NOT NULL , telf VARCHAR NOT NULL )"),
    ELIMINAR_BASURA("eliminar_basura",EliminarBasura.class,"CREATE TABLE eliminar_basura (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_eli_bas INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    UBICACION_LETRETE("ubicacion_letrete",UbicacionLetrete.class,"CREATE TABLE ubicacion_letrete (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_ubi_let INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    ELIMINAR_AGUA_SERVIDAS("eliminar_agua_ser",EliminarAguaSer.class,"CREATE TABLE eliminar_agua_ser (_id INTEGER PRIMARY KEY  NOT NULL , cod_agua_ser INTEGER NOT NULL , descripcion VARCHAR NOT NULL )"),
    RECIBE_AGUA("recibe_agua",RecibeAgua.class,"CREATE TABLE recibe_agua (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_rec_agu INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    TRATAMIENTO_AGUA("tratamiento_agua",TratamientoAgua.class,"CREATE TABLE tratamiento_agua (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_tra_agu INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    PROCEDENCIA_AGUA("procedencia_agua",ProcedenciaAgua.class,"CREATE TABLE procedencia_agua (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_pro_agu INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    COMBUSTIBLE_COCINA("combustible_cocinar",CombustibleCocinar.class,"CREATE TABLE combustible_cocinar (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_coc INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    ESTADO_PISO("estado_piso",EstadoPiso.class,"CREATE TABLE estado_piso (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_est_pis INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    ESTADO_TECHO("estado_techo",EstadoTecho.class,"CREATE TABLE estado_techo (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_est_tech INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    MATERIAL_PARED("material_pared",MaterialPared.class,"CREATE TABLE material_pared (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_mat_par INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    MATERIAL_PISO("material_piso",MaterialPiso.class,"CREATE TABLE material_piso (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_mat_pis INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    MATERIAL_TECHO("material_techo",MaterialTecho.class,"CREATE TABLE material_techo (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_mat_tec INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    TIPO_TRANSPORTE("tipo_transp",TipoTransp.class,"CREATE TABLE tipo_transp (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_tip_trans INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    VIAS_ACCESO("vias_acceso",ViasAcceso.class,"CREATE TABLE vias_acceso (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_via_acc INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    TIPO_VIVIENDA("tipo_vivienda",TipoVivienda.class,"CREATE TABLE tipo_vivienda (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_tip_viv INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    CONDICION_OCUPACION("condicion_ocupacion",CondicionOcupacion.class,"CREATE TABLE condicion_ocupacion (_id INTEGER PRIMARY KEY  NOT NULL  DEFAULT (null) ,cod_ocup INTEGER NOT NULL ,descripcion VARCHAR NOT NULL )"),
    NACIONALIDAD("nacionalidad",Nacionalidad.class,"CREATE TABLE nacionalidad (_id INTEGER PRIMARY KEY  NOT NULL , cod_nac VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    NACIONALIDADES("nacionalidades",Nacionalidade.class,"CREATE TABLE nacionalidades (_id INTEGER PRIMARY KEY  NOT NULL , cod_nacs VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    ETNIA("etnia",Etnia.class,"CREATE TABLE etnia (_id INTEGER PRIMARY KEY  NOT NULL , cod_etn VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    SEGPUBLICO("seguro_publico",SeguroPublico.class,"CREATE TABLE seguro_publico (_id INTEGER PRIMARY KEY  NOT NULL , cod_seg_pub VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    PARENT_JE_HO("parentesco_jefe_hogar",ParentescoJefeHogar.class,"CREATE TABLE parentesco_jefe_hogar (_id INTEGER PRIMARY KEY  NOT NULL , cod_par_jh VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    ESTADO_CIVIL("estado_civil",EstadoCivil.class,"CREATE TABLE estado_civil (_id INTEGER PRIMARY KEY  NOT NULL , cod_est_civ VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    NIVEL_INSTRS("nivel_instruccion",NivelInstruccion.class,"CREATE TABLE nivel_instruccion (_id INTEGER PRIMARY KEY  NOT NULL , cod_niv_inst VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    ACTIVIADAD_TRABAJO("actividad_trab",ActividadTrab.class,"CREATE TABLE actividad_trab (_id INTEGER PRIMARY KEY  NOT NULL , cod_act_trab VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    PUEBLOS("pueblos",Pueblo.class,"CREATE TABLE pueblos (_id INTEGER PRIMARY KEY  NOT NULL , cod_pue VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    CLASF_DIAGNOS("clasif_diagnostico",ClasifDiagnostico.class,"CREATE TABLE clasif_diagnostico (_id INTEGER PRIMARY KEY  NOT NULL , cod_claf_diag VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )");
    String tablaName;
    Class aClass;
    String query;

    TABLES(String tablaName,Class aClass,String query) {
        this.tablaName = tablaName;
        this.aClass=aClass;
        this.query=query;
    }

    public String getTablaName() {
        return tablaName;
    }

    public void setTablaName(String tablaName) {
        this.tablaName = tablaName;
    }

    public static TABLES findByTableName(String tablaName){
        for(TABLES tables:values()){
            if(tables.getTablaName().equals(tablaName))
                return tables;
        }
        return null;
    }

    public Class getaClass() {
        return aClass;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
