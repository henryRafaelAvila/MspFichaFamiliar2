package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Personas;

public class NuevoFormularioActivity extends AppCompatActivity {
    /*
        Adaptadores para los Spinners
         */
    SimpleCursorAdapter adapterNacionalidad;
    SimpleCursorAdapter adapterOcupacion;
    SimpleCursorAdapter adapterRecibeAgua;
    Spinner spinnerNacionalida;
    Spinner spinnerOcupacion;
    Spinner recibe_agua;
    DatabaseHandler db;
    Button miembrosHogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_formulario);
        /*
        Iniciando nuestro origen de datos
         */
        db = new DatabaseHandler(this);

        /*
        Obteniendo las instancias de los Spinners
         */
        spinnerNacionalida = (Spinner)findViewById(R.id.spinnerNacionalidad);
        //recibe_agua=(Spinner)findViewById(R.id.recibe_agua);

        adapterNacionalidad = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,//Layout simple
                db.getAllCocina(),//Todos los registros
                new String[]{"descripcion"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco
        spinnerNacionalida.setAdapter(adapterNacionalidad);


        spinnerOcupacion = (Spinner)findViewById(R.id.spinnerOcupacion);

        adapterOcupacion = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,//Layout simple
                db.getAllOcupacion(),//Todos los registros
                new String[]{"descripcion"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco
        spinnerOcupacion.setAdapter(adapterOcupacion);
        miembrosHogar=(Button)findViewById(R.id.miembrosHogar);
        miembrosHogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PersonaListActivity.class);
                intent.putExtra(PersonaListActivity.FORM_ID, "-1");
                context.startActivity(intent);

            }

        });

    }
}
