package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by henry on 4/2/2017.
 */

public class Formulario {
    //TODO campos generados en su version incial por pruebas de datos
    String nombre;
    String descripcion;
    String zona;
    int id;
    //TODO fin campos generados en su version incial por pruebas de datos
    @SerializedName("id_formulario")
    private Integer idFormulario;

    @SerializedName("codigo")
    private Integer codigo;

    @SerializedName("anim_conv_inad")
    private Integer animConvInad;

    @SerializedName("anim_intradomi")
    private Integer animIntradomi;

    @SerializedName("anim_vec_trans")
    private Integer animVecTrans;

    @SerializedName("calle_principal")
    private String callePrincipal;

    @SerializedName("calle_sec")
    private String calleSec;

    @SerializedName("celular")
    private String celular;

    @SerializedName("contamina_agu")
    private Integer contaminaAgu;

    @SerializedName("contamina_agu_desc")
    private String contaminaAguDesc;

    @SerializedName("contamina_air")
    private Integer contaminaAir;

    @SerializedName("contamina_air_desc")
    private String contaminaAirDesc;

    @SerializedName("contamina_suel")
    private Integer contaminaSuel;

    @SerializedName("contamina_suel_desc")
    private String contaminaSuelDesc;

    @SerializedName("entr_cell")
    private String entrCell;

    @SerializedName("entr_telf")
    private String entrTelf;

    @SerializedName("fecha_visita")
    private Date fechaVisita;

    @SerializedName("hogar")
    private String hogar;

    @SerializedName("id_comb_coc")
    private Integer idCombCoc;

    @SerializedName("id_cond_ocup")
    private Integer idCondOcup;

    @SerializedName("id_eli_bas")
    private Integer idEliBas;

    @SerializedName("id_est_par")
    private Integer idEstPar;

    @SerializedName("id_est_pis")
    private Integer idEstPis;

    @SerializedName("id_est_tech")
    private Integer idEstTech;

    @SerializedName("id_mat_par")
    private Integer idMatPar;

    @SerializedName("id_mat_pis")
    private Integer idMatPis;

    @SerializedName("id_mat_tec")
    private Integer idMatTec;

    @SerializedName("id_pro_agudd")
    private Integer idProAgudd;

    @SerializedName("id_rec_agu")
    private Integer idRecAgu;

    @SerializedName("id_tip_trans")
    private Integer idTipTrans;

    @SerializedName("id_tip_viv_id")
    private Integer idTipVivId;

    @SerializedName("id_tra_agu")
    private Integer idTraAgu;

    @SerializedName("id_ubi_let")
    private Integer idUbiLet;

    @SerializedName("id_unid_oper")
    private Integer idUnidOper;

    @SerializedName("id_via_acc")
    private Integer idViaAcc;

    @SerializedName("num_cuar")
    private String numCuar;

    @SerializedName("num_dorm")
    private String numDorm;

    @SerializedName("pers_ref_cell")
    private String persRefCell;

    @SerializedName("pers_ref_telf")
    private String persRefTelf;

    @SerializedName("riesgo_incend")
    private Integer riesgoIncend;

    @SerializedName("rs_aisla")
    private Integer rsAisla;

    @SerializedName("rs_dest_fami")
    private Integer rsDestFami;

    @SerializedName("rs_num_alcoh")
    private Integer rsNumAlcoh;

    @SerializedName("rs_num_drog_dep")
    private Integer rsNumDrogDep;

    @SerializedName("rs_num_nin_noescola")
    private Integer rsNumNinNoescola;

    @SerializedName("rs_num_sin_escol")
    private Integer rsNumSinEscol;

    @SerializedName("rs_prob_gra_fam")
    private Integer rsProbGraFam;

    @SerializedName("rs_psico_soc")
    private Integer rsPsicoSoc;

    @SerializedName("rs_viol_fami")
    private Integer rsViolFami;

    @SerializedName("rsan_aepi")
    private Integer rsanAepi;

    @SerializedName("rsan_coc_inhog")
    private Integer rsanCocInhog;

    @SerializedName("rsan_mosquit")
    private Integer rsanMosquit;

    @SerializedName("rsan_plaguicida")
    private Integer rsanPlaguicida;

    @SerializedName("rsan_rech_uni_sal")
    private Integer rsanRechUniSal;

    @SerializedName("rsan_sedazo")
    private Integer rsanSedazo;

    @SerializedName("rsas_abandono")
    private Integer rsasAbandono;

    @SerializedName("telefono")
    private String telefono;


    @SerializedName("tiem_viv_meses")
    private Integer tiemVivMeses;

    @SerializedName("tiemp_viv_anios")
    private Integer tiempVivAnios;

    @SerializedName("tiempo_transporte")
    private Integer tiempoTransporte;

    @SerializedName("ubi_zon_inun")
    private Integer ubiZonInun;

    //bi-directional many-to-one association to Mortalidad
    private List<Mortalidad> mortalidads;

    //bi-directional many-to-one association to Persona
    private List<Personas> personas;

    public Formulario() {
    }


    public Formulario(String nombre, String descripcion, String zona, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
        this.id=id;
    }

//TODO campos generados en su version incial por pruebas de datos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    //todo fin de campos version incial


    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getAnimConvInad() {
        return animConvInad;
    }

    public void setAnimConvInad(Integer animConvInad) {
        this.animConvInad = animConvInad;
    }

    public Integer getAnimIntradomi() {
        return animIntradomi;
    }

    public void setAnimIntradomi(Integer animIntradomi) {
        this.animIntradomi = animIntradomi;
    }

    public Integer getAnimVecTrans() {
        return animVecTrans;
    }

    public void setAnimVecTrans(Integer animVecTrans) {
        this.animVecTrans = animVecTrans;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public String getCalleSec() {
        return calleSec;
    }

    public void setCalleSec(String calleSec) {
        this.calleSec = calleSec;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getContaminaAgu() {
        return contaminaAgu;
    }

    public void setContaminaAgu(Integer contaminaAgu) {
        this.contaminaAgu = contaminaAgu;
    }

    public String getContaminaAguDesc() {
        return contaminaAguDesc;
    }

    public void setContaminaAguDesc(String contaminaAguDesc) {
        this.contaminaAguDesc = contaminaAguDesc;
    }

    public Integer getContaminaAir() {
        return contaminaAir;
    }

    public void setContaminaAir(Integer contaminaAir) {
        this.contaminaAir = contaminaAir;
    }

    public String getContaminaAirDesc() {
        return contaminaAirDesc;
    }

    public void setContaminaAirDesc(String contaminaAirDesc) {
        this.contaminaAirDesc = contaminaAirDesc;
    }

    public Integer getContaminaSuel() {
        return contaminaSuel;
    }

    public void setContaminaSuel(Integer contaminaSuel) {
        this.contaminaSuel = contaminaSuel;
    }

    public String getContaminaSuelDesc() {
        return contaminaSuelDesc;
    }

    public void setContaminaSuelDesc(String contaminaSuelDesc) {
        this.contaminaSuelDesc = contaminaSuelDesc;
    }

    public String getEntrCell() {
        return entrCell;
    }

    public void setEntrCell(String entrCell) {
        this.entrCell = entrCell;
    }

    public String getEntrTelf() {
        return entrTelf;
    }

    public void setEntrTelf(String entrTelf) {
        this.entrTelf = entrTelf;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyy");
        Date date = null;
        try {
            format.parse(fechaVisita);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.fechaVisita = date;
    }

    public String getHogar() {
        return hogar;
    }

    public void setHogar(String hogar) {
        this.hogar = hogar;
    }

    public Integer getIdCombCoc() {
        return idCombCoc;
    }

    public void setIdCombCoc(Integer idCombCoc) {
        this.idCombCoc = idCombCoc;
    }

    public Integer getIdCondOcup() {
        return idCondOcup;
    }

    public void setIdCondOcup(Integer idCondOcup) {
        this.idCondOcup = idCondOcup;
    }

    public Integer getIdEliBas() {
        return idEliBas;
    }

    public void setIdEliBas(Integer idEliBas) {
        this.idEliBas = idEliBas;
    }

    public Integer getIdEstPar() {
        return idEstPar;
    }

    public void setIdEstPar(Integer idEstPar) {
        this.idEstPar = idEstPar;
    }

    public Integer getIdEstPis() {
        return idEstPis;
    }

    public void setIdEstPis(Integer idEstPis) {
        this.idEstPis = idEstPis;
    }

    public Integer getIdEstTech() {
        return idEstTech;
    }

    public void setIdEstTech(Integer idEstTech) {
        this.idEstTech = idEstTech;
    }

    public Integer getIdMatPar() {
        return idMatPar;
    }

    public void setIdMatPar(Integer idMatPar) {
        this.idMatPar = idMatPar;
    }

    public Integer getIdMatPis() {
        return idMatPis;
    }

    public void setIdMatPis(Integer idMatPis) {
        this.idMatPis = idMatPis;
    }

    public Integer getIdMatTec() {
        return idMatTec;
    }

    public void setIdMatTec(Integer idMatTec) {
        this.idMatTec = idMatTec;
    }

    public Integer getIdProAgudd() {
        return idProAgudd;
    }

    public void setIdProAgudd(Integer idProAgudd) {
        this.idProAgudd = idProAgudd;
    }

    public Integer getIdRecAgu() {
        return idRecAgu;
    }

    public void setIdRecAgu(Integer idRecAgu) {
        this.idRecAgu = idRecAgu;
    }

    public Integer getIdTipTrans() {
        return idTipTrans;
    }

    public void setIdTipTrans(Integer idTipTrans) {
        this.idTipTrans = idTipTrans;
    }

    public Integer getIdTipVivId() {
        return idTipVivId;
    }

    public void setIdTipVivId(Integer idTipVivId) {
        this.idTipVivId = idTipVivId;
    }

    public Integer getIdTraAgu() {
        return idTraAgu;
    }

    public void setIdTraAgu(Integer idTraAgu) {
        this.idTraAgu = idTraAgu;
    }

    public Integer getIdUbiLet() {
        return idUbiLet;
    }

    public void setIdUbiLet(Integer idUbiLet) {
        this.idUbiLet = idUbiLet;
    }

    public Integer getIdUnidOper() {
        return idUnidOper;
    }

    public void setIdUnidOper(Integer idUnidOper) {
        this.idUnidOper = idUnidOper;
    }

    public Integer getIdViaAcc() {
        return idViaAcc;
    }

    public void setIdViaAcc(Integer idViaAcc) {
        this.idViaAcc = idViaAcc;
    }

    public String getNumCuar() {
        return numCuar;
    }

    public void setNumCuar(String numCuar) {
        this.numCuar = numCuar;
    }

    public String getNumDorm() {
        return numDorm;
    }

    public void setNumDorm(String numDorm) {
        this.numDorm = numDorm;
    }

    public String getPersRefCell() {
        return persRefCell;
    }

    public void setPersRefCell(String persRefCell) {
        this.persRefCell = persRefCell;
    }

    public String getPersRefTelf() {
        return persRefTelf;
    }

    public void setPersRefTelf(String persRefTelf) {
        this.persRefTelf = persRefTelf;
    }

    public Integer getRiesgoIncend() {
        return riesgoIncend;
    }

    public void setRiesgoIncend(Integer riesgoIncend) {
        this.riesgoIncend = riesgoIncend;
    }

    public Integer getRsAisla() {
        return rsAisla;
    }

    public void setRsAisla(Integer rsAisla) {
        this.rsAisla = rsAisla;
    }

    public Integer getRsDestFami() {
        return rsDestFami;
    }

    public void setRsDestFami(Integer rsDestFami) {
        this.rsDestFami = rsDestFami;
    }

    public Integer getRsNumAlcoh() {
        return rsNumAlcoh;
    }

    public void setRsNumAlcoh(Integer rsNumAlcoh) {
        this.rsNumAlcoh = rsNumAlcoh;
    }

    public Integer getRsNumDrogDep() {
        return rsNumDrogDep;
    }

    public void setRsNumDrogDep(Integer rsNumDrogDep) {
        this.rsNumDrogDep = rsNumDrogDep;
    }

    public Integer getRsNumNinNoescola() {
        return rsNumNinNoescola;
    }

    public void setRsNumNinNoescola(Integer rsNumNinNoescola) {
        this.rsNumNinNoescola = rsNumNinNoescola;
    }

    public Integer getRsNumSinEscol() {
        return rsNumSinEscol;
    }

    public void setRsNumSinEscol(Integer rsNumSinEscol) {
        this.rsNumSinEscol = rsNumSinEscol;
    }

    public Integer getRsProbGraFam() {
        return rsProbGraFam;
    }

    public void setRsProbGraFam(Integer rsProbGraFam) {
        this.rsProbGraFam = rsProbGraFam;
    }

    public Integer getRsPsicoSoc() {
        return rsPsicoSoc;
    }

    public void setRsPsicoSoc(Integer rsPsicoSoc) {
        this.rsPsicoSoc = rsPsicoSoc;
    }

    public Integer getRsViolFami() {
        return rsViolFami;
    }

    public void setRsViolFami(Integer rsViolFami) {
        this.rsViolFami = rsViolFami;
    }

    public Integer getRsanAepi() {
        return rsanAepi;
    }

    public void setRsanAepi(Integer rsanAepi) {
        this.rsanAepi = rsanAepi;
    }

    public Integer getRsanCocInhog() {
        return rsanCocInhog;
    }

    public void setRsanCocInhog(Integer rsanCocInhog) {
        this.rsanCocInhog = rsanCocInhog;
    }

    public Integer getRsanMosquit() {
        return rsanMosquit;
    }

    public void setRsanMosquit(Integer rsanMosquit) {
        this.rsanMosquit = rsanMosquit;
    }

    public Integer getRsanPlaguicida() {
        return rsanPlaguicida;
    }

    public void setRsanPlaguicida(Integer rsanPlaguicida) {
        this.rsanPlaguicida = rsanPlaguicida;
    }

    public Integer getRsanRechUniSal() {
        return rsanRechUniSal;
    }

    public void setRsanRechUniSal(Integer rsanRechUniSal) {
        this.rsanRechUniSal = rsanRechUniSal;
    }

    public Integer getRsanSedazo() {
        return rsanSedazo;
    }

    public void setRsanSedazo(Integer rsanSedazo) {
        this.rsanSedazo = rsanSedazo;
    }

    public Integer getRsasAbandono() {
        return rsasAbandono;
    }

    public void setRsasAbandono(Integer rsasAbandono) {
        this.rsasAbandono = rsasAbandono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTiemVivMeses() {
        return tiemVivMeses;
    }

    public void setTiemVivMeses(Integer tiemVivMeses) {
        this.tiemVivMeses = tiemVivMeses;
    }

    public Integer getTiempVivAnios() {
        return tiempVivAnios;
    }

    public void setTiempVivAnios(Integer tiempVivAnios) {
        this.tiempVivAnios = tiempVivAnios;
    }

    public Integer getTiempoTransporte() {
        return tiempoTransporte;
    }

    public void setTiempoTransporte(Integer tiempoTransporte) {
        this.tiempoTransporte = tiempoTransporte;
    }

    public Integer getUbiZonInun() {
        return ubiZonInun;
    }

    public void setUbiZonInun(Integer ubiZonInun) {
        this.ubiZonInun = ubiZonInun;
    }

    public List<Mortalidad> getMortalidads() {
        return mortalidads;
    }

    public void setMortalidads(List<Mortalidad> mortalidads) {
        this.mortalidads = mortalidads;
    }

    public List<Personas> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Personas> personas) {
        this.personas = personas;
    }
}
