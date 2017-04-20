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
    }

    private void saveLead(Formulario lead) {
        myFormulario.put(lead.getNombre(), lead);
    }

    public List<Formulario> getAllFormularios() {
        return new ArrayList<>(myFormulario.values());
    }
}
