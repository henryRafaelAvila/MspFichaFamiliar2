package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import z9.msp.gob.persistencia.utils.Utilitarios;

/**
 * Created by henry on 4/9/2017.
 */

public class Mortalidad implements Serializable {

    @Expose(serialize = false)
    transient int image;
    //
    @SerializedName("id_mortalida")
    private Integer idMortalida;

    private String apellidos;

    private String causa;

    private String cedula;

    private Integer edad;

    @SerializedName("fecha_muerte")
    private Date fechaMuerte;

    @SerializedName("id_par_jh")
    private Integer idParJh;

    private String nombres;

    private Integer sexo;

    @Expose(serialize = false)
    private Integer idFormulario;

    public Mortalidad() {
    }

    public Mortalidad(String nombres, String numCedula, String fechaMuerte, int image) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyy");
        Date date = null;
        try {
            format.parse(fechaMuerte);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.nombres = nombres;
        this.cedula = numCedula;
        this.fechaMuerte = date;
        this.image = image;
    }

    public String getNumCedula() {
        return cedula;
    }

    public void setNumCedula(String numCedula) {
        this.cedula = numCedula;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Integer getIdMortalida() {
        return idMortalida;
    }

    public void setIdMortalida(Integer idMortalida) {
        this.idMortalida = idMortalida;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        this.fechaMuerte = new Utilitarios().stringToDate(fechaMuerte);
    }

    public Integer getIdParJh() {
        return idParJh;
    }

    public void setIdParJh(Integer idParJh) {
        this.idParJh = idParJh;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

