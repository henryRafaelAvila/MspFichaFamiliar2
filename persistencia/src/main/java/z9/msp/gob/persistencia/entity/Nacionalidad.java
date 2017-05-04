package z9.msp.gob.persistencia.entity;

/**
 * Created by henry 3/26/2017.
 */
public class Nacionalidad {
    public int id_nac;
    public int cod_nac;
    public String descripcion;

    public int getId_nac() {
        return id_nac;
    }

    public void setId_nac(int id_nac) {
        this.id_nac = id_nac;
    }

    public int getCod_nac() {
        return cod_nac;
    }

    public void setCod_nac(int cod_nac) {
        this.cod_nac = cod_nac;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
