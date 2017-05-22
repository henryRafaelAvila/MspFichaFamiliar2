package z9.msp.gob.persistencia.entity;

import java.util.List;

/**
 * Created by henry on 4/2/2017.
 */

public class Formulario {
    String nombre;
    String descripcion;
    String zona;
    int id;


    public Formulario() {
    }

    public Formulario(String nombre, String descripcion, String zona, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
        this.id=id;
    }
    private List<Personas> personas;

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

    public List<Personas> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Personas> personas) {
        this.personas = personas;
    }
}
