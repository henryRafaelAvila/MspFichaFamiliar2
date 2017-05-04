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

    @SerializedName("id_cat_ocu")
    private Integer idCatOcu;

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

    public Personas(String nombres, String numCedula, String fechaNac, int image) {
        this.nombres = nombres;
        this.numCedula = numCedula;
        this.fechaNac = fechaNac;
        this.image = image;
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
}
