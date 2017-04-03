package z9.msp.gob.mspfichafamiliar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;

public class NuevoFormularioActivity extends AppCompatActivity {
    /*
        Adaptadores para los Spinners
         */
    SimpleCursorAdapter adapterNacionalidad;
    Spinner spinnerNacionalida;
    DatabaseHandler db;
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


        /*
        Creando Adaptador para GenreSpinner
         */
      /*  String[] spinnerLists = db.getAllNacionalidad();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(NuevoFormularioActivity.this,android.R.layout.simple_spinner_item, spinnerLists);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNacionalida.setAdapter(spinnerAdapter);
        spinnerNacionalida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


        spinnerNacionalida.setAdapter(adapterNacionalidad);

    }
});
    */
         /*
        Creando Adaptador para GenreSpinner
         */
        adapterNacionalidad = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,//Layout simple
                db.getAllCocina(),//Todos los registros
                new String[]{"descripcion"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco



        /*
        descripcion
        descripcion
        Seteando Adaptador de GenreSpinner
         */
        spinnerNacionalida.setAdapter(adapterNacionalidad);
    }
}
