package z9.msp.gob.persistencia.entity;

/**
 * Created by henry on 4/2/2017.
 */

public class Formulario {
    String nombre;
    String descripcion;
    String zona;
    int image;

    public Formulario(String nombre, String descripcion, String zona,int image) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
}
