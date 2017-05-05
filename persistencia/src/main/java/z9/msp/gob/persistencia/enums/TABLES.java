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

/**
 * Created by henry on 4/20/2017.
 */

public enum TABLES {
    CONFIG_SERVER("config_server",null,"CREATE TABLE config_server (_id INTEGER PRIMARY KEY  NOT NULL , ip VARCHAR NOT NULL , puerto INTEGER NOT NULL,servicio_cat VARCHAR,servicio_up VARCHAR,servicio_down VARCHAR,usuario VARCHAR,clave VARCHAR)"),
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
    CLASF_DIAGNOS("clasif_diagnostico",ClasifDiagnostico.class,"CREATE TABLE clasif_diagnostico (_id INTEGER PRIMARY KEY  NOT NULL , cod_claf_diag VARCHAR NOT NULL , descripcion VARCHAR NOT NULL )"),
    FORMULARIO("formulario", Formulario.class,"CREATE TABLE formulario(_id INTEGER PRIMARY KEY  NOT NULL,id_mat_pis integer,id_mat_par integer,id_pro_agudd integer,id_est_tech integer,id_est_par integer,id_rec_agu integer,id_cond_ocup integer,id_tra_agu integer,id_via_acc integer,id_comb_coc integer,id_tip_trans integer,id_est_pis integer,id_eli_bas integer,id_tip_viv_id integer,id_ubi_let integer,id_unid_oper integer,id_mat_tec integer,fecha_visita date NOT NULL,tiem_viv_meses integer,tiemp_viv_anios integer,entr_telf VARCHAR,entr_cell VARCHAR,pers_ref_telf VARCHAR,pers_ref_cell VARCHAR,hogar VARCHAR,num_cuar VARCHAR NOT NULL,num_dorm VARCHAR NOT NULL,contamina_suel integer NOT NULL,contamina_suel_desc VARCHAR NOT NULL,contamina_air integer,contamina_air_desc VARCHAR,contamina_agu integer,rs_viol_fami integer,rs_dest_fami integer,rs_prob_gra_fam integer,rs_psico_soc integer,rs_aisla integer,rs_num_sin_escol integer,rs_num_nin_noescola integer,rs_num_alcoh integer,rs_num_drog_dep integer,contamina_agu_desc VARCHAR,anim_intradomi integer,anim_vec_trans integer,anim_conv_inad integer,rsan_coc_inhog integer,rsan_sedazo integer,rsan_mosquit integer,rsan_plaguicida integer,rsan_aepi integer,rsas_abandono integer,rsan_rech_uni_sal integer,riesgo_incend integer,ubi_zon_inun integer,calle_principal VARCHAR,calle_sec VARCHAR,telefono VARCHAR,celular VARCHAR,tiempo_transporte integer)"),
    PERSONAS("persona",Personas.class,"CREATE TABLE persona(_id INTEGER PRIMARY KEY  NOT NULL,id_etn integer ,id_formulario integer,id_est_civ integer,id_pue integer,id_niv_inst integer,id_par_jh integer,id_cat_ocu integer,id_nacs integer,id_seg_pub integer,id_act_trab integer,id_nac integer,id_claf_diag integer,cedula VARCHAR,fecha_nac VARCHAR,nombres VARCHAR,apellidos VARCHAR,sexo integer,seguro_priv integer,det_seg_privado VARCHAR,fecha_diag VARCHAR,CONSTRAINT fk_persona_nacionalidades FOREIGN KEY (id_nacs)REFERENCES nacionalidades (_id),CONSTRAINT fk_persona_nacional FOREIGN KEY (id_nac)REFERENCES nacionalidad (_id),CONSTRAINT fk_persona_relations_activida FOREIGN KEY (id_act_trab)REFERENCES actividad_trab (_id) ,CONSTRAINT fk_persona_relations_categori FOREIGN KEY (id_cat_ocu)REFERENCES categoria_ocupacion (_id) ,CONSTRAINT fk_persona_relations_clasif_d FOREIGN KEY (id_claf_diag)REFERENCES clasif_diagnostico (_id) ,CONSTRAINT fk_persona_relations_estado_c FOREIGN KEY (id_est_civ)REFERENCES estado_civil (_id) ,CONSTRAINT fk_persona_relations_etnia FOREIGN KEY (id_etn)REFERENCES etnia (_id) ,CONSTRAINT fk_persona_relations_nivel_in FOREIGN KEY (id_niv_inst)REFERENCES nivel_instruccion (_id) ,CONSTRAINT fk_persona_relations_parentes FOREIGN KEY (id_par_jh)REFERENCES parentesco_jefe_hogar (_id) ,CONSTRAINT fk_persona_relations_pueblos FOREIGN KEY (id_pue)REFERENCES pueblos (_id) ,CONSTRAINT fk_persona_relations_seguro_p FOREIGN KEY (id_seg_pub)REFERENCES seguro_publico (_id) ,CONSTRAINT ckc_seguro_priv_persona CHECK (seguro_priv IS NULL OR seguro_priv >= 1 AND seguro_priv <= 2),CONSTRAINT ckc_sexo_persona CHECK (sexo IS NULL OR sexo >= 1 AND sexo <= 2));"),;
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
