package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by henry on 4/9/2017.
 */

public class Personas {
    String nombres;
    String numCedula;
    int image;
    private Integer idPersona;

    private String apellidos;

    private String cedula;

    @SerializedName("det_seg_privado")
    private String detSegPrivado;

    @SerializedName("fecha_diag")
    private String fechaDiag;

    @SerializedName("fecha_nac")
    private String fechaNac;

    @SerializedName("id_act_trab")
    private Integer idActTrab;


    @SerializedName("id_claf_diag")
    private Integer idClafDiag;

    @SerializedName("id_est_civ")
    private Integer idEstCiv;

    @SerializedName("id_etn")
    private Integer idEtn;

    @SerializedName("id_nac")
    private Integer idNac;

    @SerializedName("id_nacs")
    private Integer idNacs;

    @SerializedName("id_niv_inst")
    private Integer idNivInst;

    @SerializedName("id_par_jh")
    private Integer idParJh;

    @SerializedName("id_pue")
    private Integer idPue;

    @SerializedName("id_seg_pub")
    private Integer idSegPub;

    @SerializedName("seguro_priv")
    private Integer seguroPriv;

    private Integer sexo;
    @Expose(serialize = false)
    private Integer idFormulario;
    //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();


    public Personas() {
    }

    public Personas(String nombres, String numCedula, String fechaNac, int idPersona) {
        this.nombres = nombres;
        this.numCedula = numCedula;
        this.fechaNac = fechaNac;
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDetSegPrivado() {
        return detSegPrivado;
    }

    public void setDetSegPrivado(String detSegPrivado) {
        this.detSegPrivado = detSegPrivado;
    }

    public String getFechaDiag() {
        return fechaDiag;
    }

    public void setFechaDiag(String fechaDiag) {
        this.fechaDiag = fechaDiag;
    }

    public Integer getIdActTrab() {
        return idActTrab;
    }

    public void setIdActTrab(Integer idActTrab) {
        this.idActTrab = idActTrab;
    }

    public Integer getIdClafDiag() {
        return idClafDiag;
    }

    public void setIdClafDiag(Integer idClafDiag) {
        this.idClafDiag = idClafDiag;
    }

    public Integer getIdEstCiv() {
        return idEstCiv;
    }

    public void setIdEstCiv(Integer idEstCiv) {
        this.idEstCiv = idEstCiv;
    }

    public Integer getIdEtn() {
        return idEtn;
    }

    public void setIdEtn(Integer idEtn) {
        this.idEtn = idEtn;
    }

    public Integer getIdNac() {
        return idNac;
    }

    public void setIdNac(Integer idNac) {
        this.idNac = idNac;
    }

    public Integer getIdNacs() {
        return idNacs;
    }

    public void setIdNacs(Integer idNacs) {
        this.idNacs = idNacs;
    }

    public Integer getIdNivInst() {
        return idNivInst;
    }

    public void setIdNivInst(Integer idNivInst) {
        this.idNivInst = idNivInst;
    }

    public Integer getIdParJh() {
        return idParJh;
    }

    public void setIdParJh(Integer idParJh) {
        this.idParJh = idParJh;
    }

    public Integer getIdPue() {
        return idPue;
    }

    public void setIdPue(Integer idPue) {
        this.idPue = idPue;
    }

    public Integer getIdSegPub() {
        return idSegPub;
    }

    public void setIdSegPub(Integer idSegPub) {
        this.idSegPub = idSegPub;
    }

    public Integer getSeguroPriv() {
        return seguroPriv;
    }

    public void setSeguroPriv(Integer seguroPriv) {
        this.seguroPriv = seguroPriv;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }
}
