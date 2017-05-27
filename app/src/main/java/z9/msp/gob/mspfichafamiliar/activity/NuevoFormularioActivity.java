package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;

public class NuevoFormularioActivity extends AppCompatActivity {
    public  static final String FORMULARIO_ID="FORMULARIO_ID";
    Date fecha;
    TextView fecha_completa;
    EditText parroquia;
    EditText canton;
    EditText provincia;
    EditText distrito;
    EditText zona,croquistxt,localidadtxt,manzanatxt,edificiotxt,calle1txt,calle2txt,
            telefonotxt,celulartxt,responsabletxt;
    TextView inst;

    EditText mesestxt,aniostxt,telefonoentrevistadotxt,celularentrevistadotxt,telefonoreferenciatxt,celularreferenciatxt;
    String meses="",anios="",telefonoentrevistado="",celularentrevistado="",telefonoreferencia="",celularreferencia="",responsable="";

    EditText tiempotxt,contaminacion_suelotxt,contaminacion_airetxt,contaminacion_aguatxt,n_cuartostxt,ncuartos_dormirtxt;
    Switch intradomiciliariosw,vectoresw,animales_sueltosw;
    Switch violenciaintrasw,desetructuracionsw,problemafamisw,problemasocialesw,aislamientosw,escolarizacionsw,insertadosw,alcoholismosw,drogasw;
    Switch cocinadormitoriosw,sedazosw,mosquiterosw,plaguicidasw,aepisw,abandonosw,ircssw;

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

    String parr="",cod_parr="", ocupacion="";
    String id_canton="",id_distrito="",id_zona="",zona1="";
    String canton1="", id_provincia="", cod_prov="",cod_distrito="",id_formulario="";


    DatabaseHandler db;
    Button miembrosHogar;
    Button mortalidad;
    Button guardar;

    String fechacComplString="",coordenadas="",localidad="",manzana="",edificio=""
            ,calle1="",calle2="",telefono="",celular="",cod_ocupacion="",institucion="";

    String tipo_vivienda="",acceso_vivienda="",transporte="",tiempo="",material_techo="",material_piso="";
    String material_paredes="",estado_techo="",estado_piso="",combustible="",n_cuartos="",n_cuartos_dormir="";
    String agua_proviene="",agua_recibe="",agua_tratamiento="",agua_servidas="",letrete_ubicacion="",basura="";
    String contaminacion_suelo="",contaminacion_aire="",contaminacion_agua="";
    String intradomiciliarios="1",vectores="1",animales_sueltos="1";
    String violenciaintra="1",desestructuracion="1",problemafami="1",problemasocio="1",aislamiento="1",escolariza="1",
            noinsertados="1",alcoholismo="1",drogas="1";
    String cocinadormitorio="1",sedazo="1",mosquitero="1",plaguicida="1",aepi="1",abandono="1",ircs="1",cod_unidad="";

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
        fechacComplString = fecc.format(fecha);
        fecha_completa.setText(fechacComplString);

        inst= (TextView) findViewById(R.id.txtinst);
        croquistxt=(EditText) findViewById(R.id.etxtCroquis);
        croquistxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ( !hasFocus || hasFocus ) {
                    String valor = (croquistxt.getText().toString() );
                    if ( valor.isEmpty()) {
                        croquistxt.setError( "Ingrese las coordenadas");

                    }
                }
            }
        });
        localidadtxt=(EditText) findViewById(R.id.etxLocalidad);
        manzanatxt =(EditText) findViewById(R.id.etxManzana);
        edificiotxt  =(EditText) findViewById(R.id.etxEdificio);
        calle1txt=(EditText) findViewById(R.id.etxtCalle1);
        calle2txt=(EditText) findViewById(R.id.etxtCalle2);
        telefonotxt=(EditText) findViewById(R.id.etxtFono);
        celulartxt=(EditText) findViewById(R.id.etxtCelular);


        mesestxt=(EditText) findViewById(R.id.etxtMeses);
        mesestxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ( !hasFocus ) {
                    int valor = Integer.parseInt(mesestxt.getText().toString() );
                    if ( valor > 11 ) {
                        mesestxt.setError( "Ingrese solo valores menores igual a 11");
                        mesestxt.setText("");
                        mesestxt.requestFocus();
                    }
                }
            }
        });
        aniostxt=(EditText) findViewById(R.id.etxtAnios);
        telefonoentrevistadotxt=(EditText) findViewById(R.id.etxtFonoFijo);
        celularentrevistadotxt=(EditText) findViewById(R.id.etxtCelularEntrevistado);
        telefonoreferenciatxt=(EditText) findViewById(R.id.etxtFonoFijoReferencia);
        celularreferenciatxt=(EditText) findViewById(R.id.etxtCelularReferencia);


        /*
        Obteniendo las instancias de los Spinners
         */

        spinnerUnidaOperativa = (Spinner)findViewById(R.id.spinnerUnidad);
        adapterUnidaOperativa= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,//Layout simple
                db.getAllGeneric("unidad_operativa"),//Todos los registros
                new String[]{"nombre_oficial"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco
        spinnerUnidaOperativa.setAdapter(adapterUnidaOperativa);

        spinnerUnidaOperativa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Obteniendo el id del Spinner que recibió el evento
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerUnidad:
                        //Obteniendo el id de la parroquia seleccionado
                        Cursor c1 = (Cursor) parent.getItemAtPosition(position);
                        parr = c1.getString(
                                c1.getColumnIndex(db.getAllGeneric("unidad_operativa").getColumnName(1)));
                        cod_unidad=c1.getString(
                                c1.getColumnIndex(db.getAllGeneric("unidad_operativa").getColumnName(0)));
                        System.out.println(cod_ocupacion);
                        break;
                }
                    DatosParroquia(parr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTipoVivienda = (Spinner)findViewById(R.id.spinnerTipoVivienda);
        adapterTipoVivienda= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("tipo_vivienda"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTipoVivienda.setAdapter(adapterTipoVivienda);
        spinnerTipoVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerTipoVivienda:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        tipo_vivienda= c.getString(
                                c.getColumnIndex(db.getAllGeneric("tipo_vivienda").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerAccesoVivienda = (Spinner)findViewById(R.id.spinnerAccesoVivienda);
        adapterAccesoVivienda= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("vias_acceso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerAccesoVivienda.setAdapter(adapterAccesoVivienda);
        spinnerAccesoVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerAccesoVivienda:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        acceso_vivienda= c.getString(
                                c.getColumnIndex(db.getAllGeneric("vias_acceso").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTipoTransporte = (Spinner)findViewById(R.id.spinnerTipoTransporte);
        adapterTipoTransporte= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("tipo_transp"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTipoTransporte.setAdapter(adapterTipoTransporte);
        spinnerTipoTransporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerTipoTransporte:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        transporte= c.getString(
                                c.getColumnIndex(db.getAllGeneric("vias_acceso").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tiempotxt=(EditText)findViewById(R.id.etxtTiempo);



        spinnerMaterialTecho = (Spinner)findViewById(R.id.spinnerMaterialTecho);
        adapterMaterialTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_techo"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialTecho.setAdapter(adapterMaterialTecho);
        spinnerMaterialTecho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerMaterialTecho:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        material_techo= c.getString(
                                c.getColumnIndex(db.getAllGeneric("material_techo").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMaterialPiso = (Spinner)findViewById(R.id.spinnerMaterialPiso);
        adapterMaterialPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_piso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPiso.setAdapter(adapterMaterialPiso);
        spinnerMaterialPiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerMaterialPiso:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        material_piso= c.getString(
                                c.getColumnIndex(db.getAllGeneric("material_piso").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMaterialPared = (Spinner)findViewById(R.id.spinnerMaterialParedes);
        adapterMaterialPared= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_pared"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPared.setAdapter(adapterMaterialPared);
        spinnerMaterialPared.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerMaterialParedes:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        material_paredes= c.getString(
                                c.getColumnIndex(db.getAllGeneric("material_pared").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEstadoTecho = (Spinner)findViewById(R.id.spinnerEstadoTecho);
        adapterEstadoTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("estado_techo"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoTecho.setAdapter(adapterEstadoTecho);
        spinnerEstadoTecho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerEstadoTecho:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        estado_techo= c.getString(
                                c.getColumnIndex(db.getAllGeneric("estado_techo").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEstadoPiso = (Spinner)findViewById(R.id.spinnerEstadoPiso);
        adapterEstadoPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("estado_piso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoPiso.setAdapter(adapterEstadoPiso);
        spinnerEstadoPiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerEstadoPiso:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        estado_piso= c.getString(
                                c.getColumnIndex(db.getAllGeneric("estado_piso").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCombustibleCocina = (Spinner)findViewById(R.id.spinnerCombustibleCocina);
        adapterCombustibleCocina = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("combustible_cocinar"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerCombustibleCocina.setAdapter(adapterCombustibleCocina);
        spinnerCombustibleCocina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerCombustibleCocina:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        combustible= c.getString(
                                c.getColumnIndex(db.getAllGeneric("combustible_cocinar").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerOcupacion = (Spinner)findViewById(R.id.spinnerOcupacion);
        adapterOcupacion = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("condicion_ocupacion"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerOcupacion.setAdapter(adapterOcupacion);


        spinnerOcupacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String condicion= "ocupada".toUpperCase();
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerOcupacion:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        ocupacion = c.getString(
                                c.getColumnIndex(db.getAllGeneric("condicion_ocupacion").getColumnName(2))).toUpperCase();
                        cod_ocupacion = c.getString(
                                c.getColumnIndex(db.getAllGeneric("condicion_ocupacion").getColumnName(0))).toUpperCase();
                        break;
                }

                if(ocupacion.trim().equals(condicion)){
                    activaComponentes (true);
                }
                else{
                    activaComponentes (false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerProvieneAgua = (Spinner)findViewById(R.id.spinnerAgua);
        adapterProvieneAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("procedencia_agua"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerProvieneAgua.setAdapter(adapterProvieneAgua);
        spinnerProvieneAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerAgua:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        agua_proviene= c.getString(
                                c.getColumnIndex(db.getAllGeneric("procedencia_agua").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerRecibeAgua = (Spinner)findViewById(R.id.spinnerAguaRecibe);
        adapterRecibeAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("recibe_agua"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerRecibeAgua.setAdapter(adapterRecibeAgua);
        spinnerRecibeAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerAguaRecibe:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        agua_recibe= c.getString(
                                c.getColumnIndex(db.getAllGeneric("recibe_agua").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerTratamientoAgua = (Spinner)findViewById(R.id.spinnerAguaBeber);
        adapterTratamientoAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("tratamiento_agua"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTratamientoAgua.setAdapter(adapterTratamientoAgua);
        spinnerTratamientoAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerAguaBeber:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        agua_tratamiento= c.getString(
                                c.getColumnIndex(db.getAllGeneric("tratamiento_agua").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEliminarAgua = (Spinner)findViewById(R.id.spinnerAguaservidas);
        adapterElimiarAgua= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("eliminar_agua_ser"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEliminarAgua.setAdapter(adapterElimiarAgua);
        spinnerEliminarAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerAguaservidas:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        agua_servidas= c.getString(
                                c.getColumnIndex(db.getAllGeneric("eliminar_agua_ser").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerUbicacionRetrete = (Spinner)findViewById(R.id.spinnerRetrete);
        adapterUbicacionRetrete= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("ubicacion_letrete"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerUbicacionRetrete.setAdapter(adapterUbicacionRetrete);
        spinnerUbicacionRetrete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerRetrete:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        letrete_ubicacion= c.getString(
                                c.getColumnIndex(db.getAllGeneric("ubicacion_letrete").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBasura = (Spinner)findViewById(R.id.spinnerBasura);
        adapterBasura = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("eliminar_basura"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerBasura.setAdapter(adapterBasura);
        spinnerBasura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idSpinner = parent.getId();
                switch(idSpinner) {
                    case R.id.spinnerBasura:
                        Cursor c = (Cursor) parent.getItemAtPosition(position);
                        basura= c.getString(
                                c.getColumnIndex(db.getAllGeneric("eliminar_basura").getColumnName(0)));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        miembrosHogar=(Button)findViewById(R.id.miembrosHogar);
        miembrosHogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PersonaListActivity.class);
                intent.putExtra(PersonaListActivity.FORM_ID, id_formulario);
                context.startActivity(intent);
            }

        });

        contaminacion_suelotxt=(EditText)findViewById(R.id.etxtContaminacionSuelo) ;
        contaminacion_airetxt=(EditText)findViewById(R.id.etxtContaminacionAire) ;
        contaminacion_aguatxt=(EditText)findViewById(R.id.etxtContaminacionAgua) ;


        intradomiciliariosw = (Switch)findViewById(R.id.switchIntradomiciliarios);
        intradomiciliariosw.setChecked(true);
        intradomiciliariosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    intradomiciliarios="1";
                }
               else {
                    intradomiciliarios="2";
                }
            }
        });

        vectoresw = (Switch)findViewById(R.id.switchVectores);
        vectoresw.setChecked(true);
        vectoresw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    vectores="1";
                }
                else {
                    vectores="2";
                }
            }
        });

        animales_sueltosw= (Switch)findViewById(R.id.switchAnimalesSueltos);
        animales_sueltosw.setChecked(true);
        animales_sueltosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    animales_sueltos="1";
                }
                else {
                    animales_sueltos="2";
                }
            }
        });

        violenciaintrasw= (Switch)findViewById(R.id.switchViolenciaIntra);
        violenciaintrasw.setChecked(true);
       violenciaintrasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    violenciaintra="1";
                }
                else {
                    violenciaintra="2";
                }
            }
        });
        desetructuracionsw= (Switch)findViewById(R.id.switchDestrucFami);
        desetructuracionsw.setChecked(true);
        desetructuracionsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    desestructuracion="1";
                }
                else {
                    desestructuracion="2";
                }
            }
        });
        problemafamisw= (Switch)findViewById(R.id.switchProblemasFami);
        problemafamisw.setChecked(true);
        problemafamisw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    problemafami="1";
                }
                else {
                    problemafami="2";
                }
            }
        });
        problemasocialesw= (Switch)findViewById(R.id.switchProblemasSocio);
        problemasocialesw.setChecked(true);
        problemasocialesw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    problemasocio="1";
                }
                else {
                    problemasocio="2";
                }
            }
        });
        aislamientosw= (Switch)findViewById(R.id.switchAislamiento);
        aislamientosw.setChecked(true);
        aislamientosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    aislamiento="1";
                }
                else {
                    aislamiento="2";
                }
            }
        });
        escolarizacionsw= (Switch)findViewById(R.id.switchEscolarizacion);
        escolarizacionsw.setChecked(true);
       escolarizacionsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    escolariza="1";
                }
                else {
                    escolariza="2";
                }
            }
        });
        insertadosw= (Switch)findViewById(R.id.switchNiniosEcuela);
        insertadosw.setChecked(true);
        insertadosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    noinsertados="1";
                }
                else {
                    noinsertados="2";
                }
            }
        });
        alcoholismosw= (Switch)findViewById(R.id.switchAlcoholismo);
        alcoholismosw.setChecked(true);
        alcoholismosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    alcoholismo="1";
                }
                else{
                    alcoholismo="2";
                }
            }
        });
        drogasw= (Switch)findViewById(R.id.switchDrogas);
        drogasw.setChecked(true);
        drogasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    drogas="1";
                }
                else {
                    drogas="2";
                }
            }
        });

        cocinadormitoriosw= (Switch)findViewById(R.id.switchCocinaDormitorio);
        cocinadormitoriosw.setChecked(true);
        cocinadormitoriosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    cocinadormitorio="1";
                }
                else {
                    cocinadormitorio="2";
                }
            }
        });
        sedazosw= (Switch)findViewById(R.id.switchSedazo);
        sedazosw.setChecked(true);
        sedazosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    sedazo="1";
                }
            }
        });
       mosquiterosw= (Switch)findViewById(R.id.switchMosquiteros);
       mosquiterosw.setChecked(true);
       mosquiterosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    mosquitero="1";
                }
                else {
                    mosquitero="2";
                }
            }
        });
        plaguicidasw= (Switch)findViewById(R.id.switchPlaguisidas);
        plaguicidasw.setChecked(true);
        plaguicidasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    plaguicida="1";
                }
                else {
                    plaguicida="2";
                }
            }
        });
        aepisw= (Switch)findViewById(R.id.switchAEPI);
        aepisw.setChecked(true);
        aepisw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    aepi="1";
                }
                else {
                    aepi="2";
                }
            }
        });
        abandonosw= (Switch)findViewById(R.id.switchAbandono);
        abandonosw.setChecked(true);
        abandonosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    abandono="1";
                }
                else {
                    abandono="2";
                }
            }
        });
        ircssw= (Switch)findViewById(R.id.switchIrUnidad);
        ircssw.setChecked(true);
        ircssw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    ircs="1";
                }
                else{
                    ircs="2";
                }
            }
        });

        n_cuartostxt=(EditText)findViewById(R.id.etxtNCuartos) ;
        ncuartos_dormirtxt=(EditText)findViewById(R.id.etxtNCuartosDormir) ;
       responsabletxt=(EditText)findViewById(R.id.etxtResponsable) ;
        responsabletxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ( !hasFocus || hasFocus ) {
                    String valor = (responsabletxt.getText().toString() );
                    if ( valor.isEmpty()) {
                        responsabletxt.setError( "Ingrese losa datos solicitados");

                    }
                }
            }
        });


        mortalidad=(Button)findViewById(R.id.mortalidad);
        mortalidad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MortalidadListActivity.class);
                intent.putExtra(MortalidadListActivity.FORM_ID, id_formulario);
                context.startActivity(intent);
            }

        });
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id_formulario= null;
            } else {
                id_formulario= extras.getString(FORMULARIO_ID);
                initBotones();
            }
        } else {
            id_formulario= (String) savedInstanceState.getSerializable(FORMULARIO_ID);
        }
        guardar=(Button)findViewById(R.id.btnguardar);
        guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String condicion= "ocupada".toUpperCase();
                responsable=responsabletxt.getText().toString();
                institucion = inst.getText().toString();
                coordenadas=croquistxt.getText().toString();
                localidad=localidadtxt.getText().toString();
                manzana = manzanatxt.getText().toString();
                edificio=edificiotxt.getText().toString();
                calle1=calle1txt.getText().toString();
                calle2=calle2txt.getText().toString();
                telefono = telefonotxt.getText().toString();
                celular=celulartxt.getText().toString();
                meses = mesestxt.getText().toString();
                anios=aniostxt.getText().toString();
                telefonoentrevistado = telefonoentrevistadotxt.getText().toString();
                celularentrevistado=celularentrevistadotxt.getText().toString();
                telefonoreferencia=telefonoreferenciatxt.getText().toString();
                celularreferencia=celularreferenciatxt.getText().toString();
                tiempo=tiempotxt.getText().toString();
                n_cuartos=n_cuartostxt.getText().toString();
                n_cuartos_dormir=ncuartos_dormirtxt.getText().toString();
                contaminacion_suelo=contaminacion_suelotxt.getText().toString();
                contaminacion_aire=contaminacion_airetxt.getText().toString();
                contaminacion_agua=contaminacion_aguatxt.getText().toString();

                /*if(id_formulario!=null&&!id_formulario.equals("")) {
                    if (ocupacion.trim().equals(condicion)) {
                        db.insertFormulario("-1", material_piso, material_paredes, agua_proviene, estado_techo, agua_recibe, cod_ocupacion,
                                agua_tratamiento, acceso_vivienda, combustible, transporte, estado_piso, basura, agua_servidas, tipo_vivienda, letrete_ubicacion, cod_unidad, material_techo,
                                fechacComplString, meses, anios, telefonoentrevistado, celularentrevistado, telefonoreferencia, celularreferencia, n_cuartos, n_cuartos_dormir, contaminacion_suelo,
                                contaminacion_aire, violenciaintra, desestructuracion, problemafami, problemasocio, aislamiento, escolariza, noinsertados, alcoholismo, drogas, contaminacion_agua,
                                intradomiciliarios, vectores, animales_sueltos, cocinadormitorio, sedazo, mosquitero, plaguicida, aepi, abandono, ircs, calle1, calle2, telefono, celular, edificio, manzana, coordenadas, responsable, localidad, institucion, tiempo);

                    } else {
                        db.insertFormulario("-1", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", cod_unidad, "",
                                fechacComplString, "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", calle1, calle2,
                                telefono, celular, edificio, manzana, coordenadas, responsable, localidad, institucion, "");
                    }

                }*/
                if(id_formulario==null||id_formulario.equals("")) {
                    if (ocupacion.trim().equals(condicion)) {
                        id_formulario=db.insertFormulario("-1", material_piso, material_paredes, agua_proviene, estado_techo, agua_recibe, cod_ocupacion,
                                agua_tratamiento, acceso_vivienda, combustible, transporte, estado_piso, basura, agua_servidas, tipo_vivienda, letrete_ubicacion, cod_unidad, material_techo,
                                fechacComplString, meses, anios, telefonoentrevistado, celularentrevistado, telefonoreferencia, celularreferencia, n_cuartos, n_cuartos_dormir, contaminacion_suelo,
                                contaminacion_aire, violenciaintra, desestructuracion, problemafami, problemasocio, aislamiento, escolariza, noinsertados, alcoholismo, drogas, contaminacion_agua,
                                intradomiciliarios, vectores, animales_sueltos, cocinadormitorio, sedazo, mosquitero, plaguicida, aepi, abandono, ircs, calle1, calle2, telefono, celular, edificio, manzana, coordenadas, responsable, localidad, institucion, tiempo);

                    } else {
                        id_formulario=db.insertFormulario("-1", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", cod_unidad, "",
                                fechacComplString, "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", "", "", "", calle1, calle2,
                                telefono, celular, edificio, manzana, coordenadas, responsable, localidad, institucion, "");
                    }

                }

                Toast.makeText(getApplicationContext(), "Datos insertados correctamente", Toast.LENGTH_LONG).show();
                Button btbmiembrosfamilia= (Button) findViewById(R.id.miembrosHogar);
                btbmiembrosfamilia.setEnabled(true);
                Button personasfallecidas= (Button) findViewById(R.id.mortalidad);
                personasfallecidas.setEnabled(true);
                Button btbguardar= (Button) findViewById(R.id.btnguardar);
                btbguardar.setEnabled(false);

               /* Cursor cursor=db.getAllGeneric("formulario");
                cursor.moveToLast();
                id_formulario = cursor.getString(0);*/
                TextView id_formulariotxt=(TextView)findViewById(R.id.id_formulario);
                id_formulariotxt.setText(id_formulario);

            }

        });


    }
private void initBotones(){
    if(id_formulario!=null&&!id_formulario.equals("")){
        Button btbmiembrosfamilia= (Button) findViewById(R.id.miembrosHogar);
        btbmiembrosfamilia.setEnabled(true);
        Button personasfallecidas= (Button) findViewById(R.id.mortalidad);
        personasfallecidas.setEnabled(true);
        Button btbguardar= (Button) findViewById(R.id.btnguardar);
        btbguardar.setEnabled(false);
        btbguardar.setText("ACTUALIZAR");
    }
}
    public void DatosParroquia(String parr){
        Cursor cursor=db.getUnidadDatos("parroquia",parr);
        //todo: observacion. H.A por accidente estaba usando  moveToFirst()<Causa problema con un solo registro> en lugar moveToNext(), validar Natys si tu necesitas moveToFisrt
        if (cursor.moveToFirst()) {
            //Datos parroquia
            do {
                String codigo= cursor.getString(0);
                cod_parr = cursor.getString(4);
                id_canton=cursor.getString(1);
                id_distrito=cursor.getString(2);
            } while(cursor.moveToNext());
        }
        parroquia= (EditText) findViewById(R.id.etxParroquia);
        parroquia.setText(""+cod_parr);

        // Datos canton
        cursor=db.getUnidadDatos("canton",id_canton);
        if (cursor.moveToFirst()) {
            do {
                canton1=cursor.getString(3);
                id_provincia=cursor.getString(1);
                id_zona=cursor.getString(4);
            } while(cursor.moveToNext());
        }
        canton= (EditText) findViewById(R.id.etxtCanton);
       canton.setText(""+canton1);

        // Datos zona
        cursor=db.getUnidadDatos("administracion_zonal",id_zona);
        if (cursor.moveToFirst()) {
            do {
                zona1=cursor.getString(2);

            } while(cursor.moveToNext());
        }
        zona= (EditText) findViewById(R.id.etxtZona);
        zona.setText(""+zona1);

        // Datos provincia
        cursor=db.getUnidadDatos("provincia",id_provincia);
        if (cursor.moveToFirst()) {
            do {
                cod_prov=cursor.getString(2);

            } while(cursor.moveToNext());
        }
       provincia= (EditText) findViewById(R.id.etxtProvincia);
       provincia.setText(""+cod_prov);

        // Datos distrito
        cursor=db.getUnidadDatos("distrito",id_distrito);
        if (cursor.moveToFirst()) {
            do {
                cod_distrito=cursor.getString(2);

            } while(cursor.moveToNext());
        }
        distrito= (EditText) findViewById(R.id.etxtDistrito);
       distrito.setText(""+cod_distrito);
    }

    public void activaComponentes (Boolean vf){
        EditText editTextmeses= (EditText) findViewById(R.id.etxtMeses);
        editTextmeses.setEnabled(vf);
        EditText editTextanios= (EditText) findViewById(R.id.etxtAnios);
        editTextanios.setEnabled(vf);
        EditText editTextfiojoentrevistado= (EditText) findViewById(R.id.etxtFonoFijo);
        editTextfiojoentrevistado.setEnabled(vf);
        EditText editTextcelularentrevistado= (EditText) findViewById(R.id.etxtCelularEntrevistado);
        editTextcelularentrevistado.setEnabled(vf);
        EditText editTextfijoreferencia= (EditText) findViewById(R.id.etxtFonoFijoReferencia);
        editTextfijoreferencia.setEnabled(vf);
        EditText editTextcelularreferencia= (EditText) findViewById(R.id.etxtCelularReferencia);
        editTextcelularreferencia.setEnabled(vf);

        Spinner tipovivienda= (Spinner) findViewById(R.id.spinnerTipoVivienda);
        tipovivienda.setEnabled(vf);
        Spinner accesovivienda= (Spinner) findViewById(R.id.spinnerAccesoVivienda);
        accesovivienda.setEnabled(vf);
        Spinner transporte= (Spinner) findViewById(R.id.spinnerTipoTransporte);
        transporte.setEnabled(vf);
        EditText tiempotransporte= (EditText) findViewById(R.id.etxtTiempo);
        tiempotransporte.setEnabled(vf);
        Spinner materialtecho= (Spinner) findViewById(R.id.spinnerMaterialTecho);
        materialtecho.setEnabled(vf);
        Spinner materialpiso= (Spinner) findViewById(R.id.spinnerMaterialPiso);
        materialpiso.setEnabled(vf);
        Spinner materialpared= (Spinner) findViewById(R.id.spinnerMaterialParedes);
        materialpared.setEnabled(vf);
        Spinner estadotecho= (Spinner) findViewById(R.id.spinnerEstadoTecho);
        estadotecho.setEnabled(vf);
        Spinner estadopiso= (Spinner) findViewById(R.id.spinnerEstadoPiso);
        estadopiso.setEnabled(vf);
        Spinner combustible= (Spinner) findViewById(R.id.spinnerCombustibleCocina);
        combustible.setEnabled(vf);

        EditText ncuartos= (EditText) findViewById(R.id.etxtNCuartos);
        ncuartos.setEnabled(vf);
        EditText ncuartosusa= (EditText) findViewById(R.id.etxtNCuartosDormir);
        ncuartosusa.setEnabled(vf);

        Spinner agua= (Spinner) findViewById(R.id.spinnerAgua);
        agua.setEnabled(vf);
        Spinner recibeagua= (Spinner) findViewById(R.id.spinnerAguaRecibe);
        recibeagua.setEnabled(vf);
        Spinner tratamientoagua= (Spinner) findViewById(R.id.spinnerAguaBeber);
        tratamientoagua.setEnabled(vf);
        Spinner aguaservida= (Spinner) findViewById(R.id.spinnerAguaservidas);
        aguaservida.setEnabled(vf);
        Spinner retrete= (Spinner) findViewById(R.id.spinnerRetrete);
        retrete.setEnabled(vf);
        Spinner basura= (Spinner) findViewById(R.id.spinnerBasura);
        basura.setEnabled(vf);

        EditText contaminacionsuelo= (EditText) findViewById(R.id.etxtContaminacionSuelo);
        contaminacionsuelo.setEnabled(vf);
        EditText contaminacionagua= (EditText) findViewById(R.id.etxtContaminacionAgua);
        contaminacionagua.setEnabled(vf);
        EditText contaminacionaire= (EditText) findViewById(R.id.etxtContaminacionAire);
        contaminacionaire.setEnabled(vf);

        Switch intradomiciliarios= (Switch) findViewById(R.id.switchIntradomiciliarios);
        intradomiciliarios.setEnabled(vf);
        Switch vectores= (Switch) findViewById(R.id.switchVectores);
        vectores.setEnabled(vf);
        Switch animalescueltos= (Switch) findViewById(R.id.switchAnimalesSueltos);
        animalescueltos.setEnabled(vf);

        Switch violenciaintra= (Switch) findViewById(R.id.switchViolenciaIntra);
        violenciaintra.setEnabled(vf);
        Switch desetrucfami= (Switch) findViewById(R.id.switchDestrucFami);
        desetrucfami.setEnabled(vf);
        Switch problemasfamilia= (Switch) findViewById(R.id.switchProblemasFami);
        problemasfamilia.setEnabled(vf);
        Switch problemassocio= (Switch) findViewById(R.id.switchProblemasSocio);
        problemassocio.setEnabled(vf);
        Switch aislamiento= (Switch) findViewById(R.id.switchAislamiento);
        aislamiento.setEnabled(vf);
        Switch escolarizacion= (Switch) findViewById(R.id.switchEscolarizacion);
        escolarizacion.setEnabled(vf);
        Switch niniosescuela= (Switch) findViewById(R.id.switchNiniosEcuela);
        niniosescuela.setEnabled(vf);
        Switch alcohol= (Switch) findViewById(R.id.switchAlcoholismo);
        alcohol.setEnabled(vf);
        Switch drogas= (Switch) findViewById(R.id.switchDrogas);
        drogas.setEnabled(vf);

        Switch cocinadormitorio= (Switch) findViewById(R.id.switchCocinaDormitorio);
        cocinadormitorio.setEnabled(vf);
        Switch sedazo= (Switch) findViewById(R.id.switchSedazo);
        sedazo.setEnabled(vf);
        Switch mosquiteros= (Switch) findViewById(R.id.switchMosquiteros);
        mosquiteros.setEnabled(vf);
        Switch plaguisida= (Switch) findViewById(R.id.switchPlaguisidas);
        plaguisida.setEnabled(vf);
        Switch aepi= (Switch) findViewById(R.id.switchAEPI);
        aepi.setEnabled(vf);
        Switch abandono= (Switch) findViewById(R.id.switchAbandono);
        abandono.setEnabled(vf);
        Switch irunidad= (Switch) findViewById(R.id.switchIrUnidad);
        irunidad.setEnabled(vf);

        /*Button btbmiembrosfamilia= (Button) findViewById(R.id.miembrosHogar);
        btbmiembrosfamilia.setEnabled(vf);
        Button personasfallecidas= (Button) findViewById(R.id.mortalidad);
        personasfallecidas.setEnabled(vf);*/
    }



    }



