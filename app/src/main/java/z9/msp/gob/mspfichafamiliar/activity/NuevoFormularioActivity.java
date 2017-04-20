package z9.msp.gob.mspfichafamiliar.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;

public class NuevoFormularioActivity extends AppCompatActivity {
    Date fecha;
    TextView fecha_completa;
    EditText parroquia;
    EditText distrito;
    /*
        Adaptadores para los Spinners
         */
    SimpleCursorAdapter adapterUnidaOperativa;
    Spinner  spinnerUnidaOperativa;
    SimpleCursorAdapter adapterTipoVivienda;
    Spinner  spinnerTipoVivienda;
    SimpleCursorAdapter adapterAccesoVivienda;
    Spinner  spinnerAccesoVivienda;
    SimpleCursorAdapter adapterTipoTransporte;
    Spinner  spinnerTipoTransporte;
    SimpleCursorAdapter adapterMaterialTecho;
    Spinner  spinnerMaterialTecho;
    SimpleCursorAdapter adapterMaterialPiso;
    Spinner  spinnerMaterialPiso;
    SimpleCursorAdapter adapterMaterialPared;
    Spinner  spinnerMaterialPared;
    SimpleCursorAdapter adapterEstadoTecho;
    Spinner  spinnerEstadoTecho;
    SimpleCursorAdapter adapterEstadoPiso;
    Spinner  spinnerEstadoPiso;
    SimpleCursorAdapter  adapterCombustibleCocina;
    SimpleCursorAdapter adapterOcupacion;
    Spinner  spinnerCombustibleCocina;
    Spinner spinnerOcupacion;
    SimpleCursorAdapter adapterProvieneAgua;
    Spinner  spinnerProvieneAgua;
    SimpleCursorAdapter adapterRecibeAgua;
    Spinner  spinnerRecibeAgua;
    SimpleCursorAdapter adapterTratamientoAgua;
    Spinner  spinnerTratamientoAgua;
    SimpleCursorAdapter adapterElimiarAgua;
    Spinner  spinnerEliminarAgua;
    SimpleCursorAdapter adapterUbicacionRetrete;
    Spinner  spinnerUbicacionRetrete;
    SimpleCursorAdapter adapterBasura;
    Spinner  spinnerBasura;
    SimpleCursorAdapter adapterPrueba;
    Spinner  spinnerPrueba;


    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_formulario);

        /*
        Iniciando nuestro origen de datos
         */
        db = new DatabaseHandler(this);

        fecha=new Date();
        fecha_completa= (TextView) findViewById(R.id.etxtfecha);

        SimpleDateFormat fecc=new SimpleDateFormat("dd/MM/yy");
        String fechacComplString = fecc.format(fecha);
        fecha_completa.setText(fechacComplString);

        /*
        Obteniendo las instancias de los Spinners
         */

        spinnerUnidaOperativa = (Spinner)findViewById(R.id.spinnerUnidad);
        adapterUnidaOperativa= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,//Layout simple
                db.getAllUnidad(),//Todos los registros
                new String[]{"nombre_oficial"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco
        spinnerUnidaOperativa.setAdapter(adapterUnidaOperativa);

        spinnerUnidaOperativa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Obteniendo el id del Spinner que recibió el evento
                int idSpinner = parent.getId();
                String parr;
                switch(idSpinner) {

                    case R.id.spinnerUnidad:
                        //Obteniendo el id del género seleccionado
                        Cursor c1 = (Cursor) parent.getItemAtPosition(position);
                        parr = c1.getString(
                                c1.getColumnIndex(db.getAllUnidad().getColumnName(1)));

                        parroquia= (EditText) findViewById(R.id.etxParroquia);
                        parroquia.setText(parr);

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTipoVivienda = (Spinner)findViewById(R.id.spinnerTipoVivienda);
        adapterTipoVivienda= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllTipoVivienda(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTipoVivienda.setAdapter(adapterTipoVivienda);

        spinnerAccesoVivienda = (Spinner)findViewById(R.id.spinnerAccesoVivienda);
        adapterAccesoVivienda= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllAccesoVivienda(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerAccesoVivienda.setAdapter(adapterAccesoVivienda);

        spinnerTipoTransporte = (Spinner)findViewById(R.id.spinnerTipoTransporte);
        adapterTipoTransporte= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllTipoTransporte(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTipoTransporte.setAdapter(adapterTipoTransporte);

        spinnerMaterialTecho = (Spinner)findViewById(R.id.spinnerMaterialTecho);
        adapterMaterialTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllMaterialTecho(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialTecho.setAdapter(adapterMaterialTecho);

        spinnerMaterialPiso = (Spinner)findViewById(R.id.spinnerMaterialPiso);
        adapterMaterialPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllMaterialPiso(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPiso.setAdapter(adapterMaterialPiso);

        spinnerMaterialPared = (Spinner)findViewById(R.id.spinnerMaterialParedes);
        adapterMaterialPared= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllMaterialParedes(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPared.setAdapter(adapterMaterialPared);

        spinnerEstadoTecho = (Spinner)findViewById(R.id.spinnerEstadoTecho);
        adapterEstadoTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllEstadoTecho(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoTecho.setAdapter(adapterEstadoTecho);

        spinnerEstadoPiso = (Spinner)findViewById(R.id.spinnerEstadoPiso);
        adapterEstadoPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllEstadoPiso(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoPiso.setAdapter(adapterEstadoPiso);

        spinnerCombustibleCocina = (Spinner)findViewById(R.id.spinnerCombustibleCocina);
        adapterCombustibleCocina = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllCocina(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerCombustibleCocina.setAdapter(adapterCombustibleCocina);


        spinnerOcupacion = (Spinner)findViewById(R.id.spinnerOcupacion);
        adapterOcupacion = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllOcupacion(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerOcupacion.setAdapter(adapterOcupacion);

        spinnerProvieneAgua = (Spinner)findViewById(R.id.spinnerAgua);
        adapterProvieneAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllProvieneAgua(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerProvieneAgua.setAdapter(adapterProvieneAgua);

        spinnerRecibeAgua = (Spinner)findViewById(R.id.spinnerAguaRecibe);
        adapterRecibeAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllRecibeAgua(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerRecibeAgua.setAdapter(adapterRecibeAgua);

        spinnerTratamientoAgua = (Spinner)findViewById(R.id.spinnerAguaBeber);
        adapterTratamientoAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllTratamientoAgua(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTratamientoAgua.setAdapter(adapterTratamientoAgua);

        spinnerEliminarAgua = (Spinner)findViewById(R.id.spinnerAguaservidas);
        adapterElimiarAgua= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllEliminaAguaServida(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEliminarAgua.setAdapter(adapterElimiarAgua);

        spinnerUbicacionRetrete = (Spinner)findViewById(R.id.spinnerRetrete);
        adapterUbicacionRetrete= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllUbicacionRetrete(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerUbicacionRetrete.setAdapter(adapterUbicacionRetrete);

        spinnerBasura = (Spinner)findViewById(R.id.spinnerBasura);
        adapterBasura = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllBasura(),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerBasura.setAdapter(adapterBasura);

        /*spinnerPrueba = (Spinner)findViewById(R.id.spinnerPrueba);
        adapterPrueba = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getUnidadParroquia(),
                new String[]{"id_distrito"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerPrueba.setAdapter(adapterPrueba);*/
        /*int dis=db.getUnidadParroquia().getInt(3);
        distrito= (EditText) findViewById(R.id.etxtDistrito);
        distrito.setText(""+dis);*/


    }


}
