package z9.msp.gob.persistencia.entity;

/**
 * Created by henry on 4/9/2017.
 */

public class Personas {
    String nombres;
    String numCedula;
    String fechaNac;
    int image;

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
