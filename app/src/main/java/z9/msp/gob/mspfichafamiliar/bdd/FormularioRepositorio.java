package z9.msp.gob.mspfichafamiliar.bdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.entity.Formulario;

/**
 * Created by henry on 4/2/2017.
 */

public class FormularioRepositorio {
    private static FormularioRepositorio repository = new FormularioRepositorio();
    private HashMap<String, Formulario> myFormulario = new HashMap<>();

    public static FormularioRepositorio getInstance() {
        return repository;
    }

    private FormularioRepositorio() {
        for(int i=1;i<12;i++) {
            saveLead(new Formulario("Formulario "+i, "Zona 9", "Parr. Chillogallo", R.drawable.home));
        }
        //saveLead(new Formulario("Carlos Lopez", "Asistente", "Hospital Blue", R.drawable.lead_photo_2));
//        saveLead(new Formulario("Sara Bonz", "Directora de Marketing", "Electrical Parts ltd"));
//        saveLead(new Formulario("Liliana Clarence", "Diseñadora de Producto", "Creativa App"));
//        saveLead(new Formulario("Benito Peralta", "Supervisor de Ventas", "Neumáticos Press"));
//        saveLead(new Formulario("Juan Jaramillo", "CEO", "Banco Nacional"));
//        saveLead(new Formulario("Christian Steps", "CTO", "Cooperativa Verde"));
//        saveLead(new Formulario("Alexa Giraldo", "Lead Programmer", "Frutisofy"));
//        saveLead(new Formulario("Linda Murillo", "Directora de Marketing", "Seguros Boliver"));
//        saveLead(new Formulario("Lizeth Astrada", "CEO", "Concesionario Motolox"));
    }

    private void saveLead(Formulario lead) {
        myFormulario.put(lead.getNombre(), lead);
    }

    public List<Formulario> getAllFormularios() {
        return new ArrayList<>(myFormulario.values());
    }
}
