package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;

public class NuevoFormularioActivity extends AppCompatActivity {
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
    String canton1="", id_provincia="", cod_prov="",cod_distrito="";


    DatabaseHandler db;
   Button miembrosHogar;
    Button mortalidad;

    String fechacComplString="",coordenadas="",localidad="",manzana="",edificio=""
            ,calle1="",calle2="",telefono="",celular="",cod_ocupacion="",institucion="";

    String tipo_vivienda="",acceso_vivienda="",transporte="",tiempo="",material_techo="",material_piso="";
    String material_paredes="",estado_techo="",estado_piso="",combustible="",n_cuartos="",n_cuartos_dormir="";
    String agua_proviene="",agua_recibe="",agua_tratamiento="",agua_servidas="",letrete_ubicacion="",basura="";
    String contaminacion_suelo="",contaminacion_aire="",contaminacion_agua="";
    String intradomiciliarios="",vectores="",animales_sueltos="";
    String violenciaintra="",desestructuracion="",problemafami="",problemasocio="",aislamiento="",escolariza="",
            noinsertados="",alcoholismo="",drogas="";
    String cocinadormitorio="",sedazo="",mosquitero="",plaguicida="",aepi="",abandono="",ircs="",cod_unidad="";

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
        institucion = inst.getText().toString();
        croquistxt=(EditText) findViewById(R.id.etxtCroquis);
        coordenadas=croquistxt.getText().toString();
        localidadtxt=(EditText) findViewById(R.id.etxLocalidad);
        localidad=localidadtxt.getText().toString();
        manzanatxt =(EditText) findViewById(R.id.etxManzana);
        manzana = manzanatxt.getText().toString();
        edificiotxt  =(EditText) findViewById(R.id.etxEdificio);
        edificio=edificiotxt.getText().toString();
        calle1txt=(EditText) findViewById(R.id.etxtCalle1);
        calle1=calle1txt.getText().toString();
        calle2txt=(EditText) findViewById(R.id.etxtCalle2);
        calle2=calle2txt.getText().toString();
        telefonotxt=(EditText) findViewById(R.id.etxtFono);
        telefono = telefonotxt.getText().toString();
        celulartxt=(EditText) findViewById(R.id.etxtCelular);
        celular=celulartxt.getText().toString();

        mesestxt=(EditText) findViewById(R.id.etxtMeses);
        meses = mesestxt.getText().toString();
        aniostxt=(EditText) findViewById(R.id.etxtAnios);
        anios=aniostxt.getText().toString();
        telefonoentrevistadotxt=(EditText) findViewById(R.id.etxtFonoFijo);
        telefonoentrevistado = telefonoentrevistadotxt.getText().toString();
        celularentrevistadotxt=(EditText) findViewById(R.id.etxtCelularEntrevistado);
        celularentrevistado=celularentrevistadotxt.getText().toString();
        telefonoreferenciatxt=(EditText) findViewById(R.id.etxtFonoFijoReferencia);
        telefonoreferencia=telefonoreferenciatxt.getText().toString();
        celularreferenciatxt=(EditText) findViewById(R.id.etxtCelularReferencia);
        celularreferencia=celularreferenciatxt.getText().toString();

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
        tiempo=tiempotxt.getText().toString();


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
                intent.putExtra(PersonaListActivity.FORM_ID, "-1");
                context.startActivity(intent);
            }

        });

        contaminacion_suelotxt=(EditText)findViewById(R.id.etxtContaminacionSuelo) ;
        contaminacion_suelo=contaminacion_suelotxt.getText().toString();
        contaminacion_airetxt=(EditText)findViewById(R.id.etxtContaminacionAire) ;
        contaminacion_aire=contaminacion_airetxt.getText().toString();
        contaminacion_aguatxt=(EditText)findViewById(R.id.etxtContaminacionAgua) ;
        contaminacion_agua=contaminacion_aguatxt.getText().toString();

        intradomiciliariosw = (Switch)findViewById(R.id.switchIntradomiciliarios);
        intradomiciliariosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    intradomiciliarios="1";
                }
            }
        });

        vectoresw = (Switch)findViewById(R.id.switchVectores);
        vectoresw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    vectores="1";
                }
            }
        });

        animales_sueltosw= (Switch)findViewById(R.id.switchAnimalesSueltos);
        animales_sueltosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    animales_sueltos="1";
                }
            }
        });

        violenciaintrasw= (Switch)findViewById(R.id.switchViolenciaIntra);
       violenciaintrasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    violenciaintra="1";
                }
            }
        });
        desetructuracionsw= (Switch)findViewById(R.id.switchDestrucFami);
        desetructuracionsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    desestructuracion="1";
                }
            }
        });
        problemafamisw= (Switch)findViewById(R.id.switchProblemasFami);
        problemafamisw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    problemafami="1";
                }
            }
        });
        problemasocialesw= (Switch)findViewById(R.id.switchProblemasSocio);
        problemasocialesw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    problemasocio="1";
                }
            }
        });
        aislamientosw= (Switch)findViewById(R.id.switchAislamiento);
        aislamientosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    aislamiento="1";
                }
            }
        });
        escolarizacionsw= (Switch)findViewById(R.id.switchEscolarizacion);
       escolarizacionsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    escolariza="1";
                }
            }
        });
        insertadosw= (Switch)findViewById(R.id.switchNiniosEcuela);
        insertadosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    noinsertados="1";
                }
            }
        });
        alcoholismosw= (Switch)findViewById(R.id.switchAlcoholismo);
        alcoholismosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    alcoholismo="1";
                }
            }
        });
        drogasw= (Switch)findViewById(R.id.switchDrogas);
        drogasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    drogas="1";
                }
            }
        });

        cocinadormitoriosw= (Switch)findViewById(R.id.switchCocinaDormitorio);
        cocinadormitoriosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    cocinadormitorio="1";
                }
            }
        });
        sedazosw= (Switch)findViewById(R.id.switchSedazo);
        sedazosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    sedazo="1";
                }
            }
        });
       mosquiterosw= (Switch)findViewById(R.id.switchMosquiteros);
       mosquiterosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    mosquitero="1";
                }
            }
        });
        plaguicidasw= (Switch)findViewById(R.id.switchPlaguisidas);
        plaguicidasw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    plaguicida="1";
                }
            }
        });
        aepisw= (Switch)findViewById(R.id.switchAEPI);
        aepisw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    aepi="1";
                }
            }
        });
        abandonosw= (Switch)findViewById(R.id.switchAbandono);
        abandonosw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    abandono="1";
                }
            }
        });
        ircssw= (Switch)findViewById(R.id.switchIrUnidad);
        ircssw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    ircs="1";
                }
            }
        });

        n_cuartostxt=(EditText)findViewById(R.id.etxtNCuartos) ;
        n_cuartos=n_cuartostxt.getText().toString();
        ncuartos_dormirtxt=(EditText)findViewById(R.id.etxtNCuartosDormir) ;
        n_cuartos_dormir=ncuartos_dormirtxt.getText().toString();

        responsabletxt=(EditText)findViewById(R.id.etxtResponsable);
        responsable=responsabletxt.getText().toString();


        mortalidad=(Button)findViewById(R.id.mortalidad);
        mortalidad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MortalidadListActivity.class);
                intent.putExtra(MortalidadListActivity.FORM_ID, "-1");
                context.startActivity(intent);
            }

        });

    }

    public void DatosParroquia(String parr){
        Cursor cursor=db.getUnidadDatos("parroquia",parr);
        if (cursor.moveToFirst()) {
            //Datos parroquia
            do {
                String codigo= cursor.getString(0);
                cod_parr = cursor.getString(3);
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
                canton1=cursor.getString(2);
                id_provincia=cursor.getString(1);
                id_zona=cursor.getString(4);
            } while(cursor.moveToNext());
        }
        canton= (EditText) findViewById(R.id.etxtCanton);
       canton.setText(""+canton1);
/*
        // Datos zona
        cursor=db.getUnidadDatos("administracion_zonal",id_zona);
        if (cursor.moveToFirst()) {
            do {
                zona1=cursor.getString(1);

            } while(cursor.moveToNext());
        }
        zona= (EditText) findViewById(R.id.etxtZona);
        zona.setText(""+zona1);*/

        // Datos provincia
        cursor=db.getUnidadDatos("provincia",id_provincia);
        if (cursor.moveToFirst()) {
            do {
                cod_prov=cursor.getString(1);

            } while(cursor.moveToNext());
        }
       provincia= (EditText) findViewById(R.id.etxtProvincia);
       provincia.setText(""+cod_prov);

        // Datos distrito
        cursor=db.getUnidadDatos("distrito",id_distrito);
        if (cursor.moveToFirst()) {
            do {
                cod_distrito=cursor.getString(1);

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



    public void insertar(){
       // fechacComplString, institucion,cod_ocupacion,coordenadas,parr,id_canton,id_distrito,id_provincia,id_zona
        //localidad,manzana,edificio,calle1,calle2,telefono,celular
        // anios, meses,telefonoentrevistado,celularentrevistado,telefonoreferencia,celularreferencia

    }



}



