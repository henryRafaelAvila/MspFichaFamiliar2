package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    EditText zona;
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
    String id_canton="",id_distrito="";
    String canton1="", id_provincia="", cod_prov="",cod_distrito="";


    DatabaseHandler db;
   Button miembrosHogar;
    Button mortalidad;
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


        spinnerAccesoVivienda = (Spinner)findViewById(R.id.spinnerAccesoVivienda);
        adapterAccesoVivienda= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("vias_acceso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerAccesoVivienda.setAdapter(adapterAccesoVivienda);

        spinnerTipoTransporte = (Spinner)findViewById(R.id.spinnerTipoTransporte);
        adapterTipoTransporte= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("tipo_transp"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTipoTransporte.setAdapter(adapterTipoTransporte);

        spinnerMaterialTecho = (Spinner)findViewById(R.id.spinnerMaterialTecho);
        adapterMaterialTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_techo"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialTecho.setAdapter(adapterMaterialTecho);

        spinnerMaterialPiso = (Spinner)findViewById(R.id.spinnerMaterialPiso);
        adapterMaterialPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_piso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPiso.setAdapter(adapterMaterialPiso);

        spinnerMaterialPared = (Spinner)findViewById(R.id.spinnerMaterialParedes);
        adapterMaterialPared= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("material_pared"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerMaterialPared.setAdapter(adapterMaterialPared);

        spinnerEstadoTecho = (Spinner)findViewById(R.id.spinnerEstadoTecho);
        adapterEstadoTecho= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("estado_techo"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoTecho.setAdapter(adapterEstadoTecho);

        spinnerEstadoPiso = (Spinner)findViewById(R.id.spinnerEstadoPiso);
        adapterEstadoPiso= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("estado_piso"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEstadoPiso.setAdapter(adapterEstadoPiso);

        spinnerCombustibleCocina = (Spinner)findViewById(R.id.spinnerCombustibleCocina);
        adapterCombustibleCocina = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("combustible_cocinar"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerCombustibleCocina.setAdapter(adapterCombustibleCocina);


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
                        break;
                }

                if(ocupacion.equals(condicion)){
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

        spinnerRecibeAgua = (Spinner)findViewById(R.id.spinnerAguaRecibe);
        adapterRecibeAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("recibe_agua"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerRecibeAgua.setAdapter(adapterRecibeAgua);

        spinnerTratamientoAgua = (Spinner)findViewById(R.id.spinnerAguaBeber);
        adapterTratamientoAgua = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("tratamiento_agua"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerTratamientoAgua.setAdapter(adapterTratamientoAgua);

        spinnerEliminarAgua = (Spinner)findViewById(R.id.spinnerAguaservidas);
        adapterElimiarAgua= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("eliminar_agua_ser"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerEliminarAgua.setAdapter(adapterElimiarAgua);

        spinnerUbicacionRetrete = (Spinner)findViewById(R.id.spinnerRetrete);
        adapterUbicacionRetrete= new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("ubicacion_letrete"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerUbicacionRetrete.setAdapter(adapterUbicacionRetrete);

        spinnerBasura = (Spinner)findViewById(R.id.spinnerBasura);
        adapterBasura = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getAllGeneric("eliminar_basura"),
                new String[]{"descripcion"},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinnerBasura.setAdapter(adapterBasura);

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
            } while(cursor.moveToNext());
        }
        canton= (EditText) findViewById(R.id.etxtCanton);
       canton.setText(""+canton1);

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

        RadioButton animalessi=(RadioButton) findViewById(R.id.radioSi);
        animalessi.setEnabled(vf);
        RadioButton animalesno=(RadioButton) findViewById(R.id.radioNo);
        animalesno.setEnabled(vf);
        RadioButton vectoresi=(RadioButton) findViewById(R.id.radioSiVectores);
        vectoresi.setEnabled(vf);
        RadioButton vectoresno=(RadioButton) findViewById(R.id.radioNoVectores);
        vectoresno.setEnabled(vf);
        RadioButton animalessueltossi=(RadioButton) findViewById(R.id.radioSiAnimalesSueltos);
        animalessueltossi.setEnabled(vf);
        RadioButton animalessueltosno=(RadioButton) findViewById(R.id.radioNoAnimalesSueltos);
        animalessueltosno.setEnabled(vf);

        RadioButton violenciaintrafamisi=(RadioButton) findViewById(R.id.radioSiViloenciaIntra);
        violenciaintrafamisi.setEnabled(vf);
        RadioButton violenciaintrafamino=(RadioButton) findViewById(R.id.radioNoViolenciaIntra);
        violenciaintrafamino.setEnabled(vf);
        RadioButton desestruccionfamisi=(RadioButton) findViewById(R.id.radioSiDesestructuracionFamiliar);
        desestruccionfamisi.setEnabled(vf);
        RadioButton desestruccionfamino=(RadioButton) findViewById(R.id.radioNoDesestructuracionFamiliar);
        desestruccionfamino.setEnabled(vf);
        RadioButton problemafamisi=(RadioButton) findViewById(R.id.radioSiProblemasFAmilia);
        problemafamisi.setEnabled(vf);
        RadioButton problemafamino=(RadioButton) findViewById(R.id.radioNoProblemasFAmilia);
        problemafamino.setEnabled(vf);
        RadioButton problemasociosi=(RadioButton) findViewById(R.id.radioSiProblemasSocia);
        problemasociosi.setEnabled(vf);
        RadioButton problemasociono=(RadioButton) findViewById(R.id.radioNoProblemasSocia);
        problemasociono.setEnabled(vf);
        RadioButton aislamientosi=(RadioButton) findViewById(R.id.radioSiAislamiento);
        aislamientosi.setEnabled(vf);
        RadioButton aislamientono=(RadioButton) findViewById(R.id.radioNoAislamiento);
        aislamientono.setEnabled(vf);
        RadioButton escolarizacionsi=(RadioButton) findViewById(R.id.radioSiEscolarizacion);
        escolarizacionsi.setEnabled(vf);
        RadioButton escolarizacionno=(RadioButton) findViewById(R.id.radioNoEscolarizacion);
        escolarizacionno.setEnabled(vf);
        RadioButton niniosescuelasi=(RadioButton) findViewById(R.id.radioSiNiniosnoescuela);
        niniosescuelasi.setEnabled(vf);
        RadioButton niniosescuelano=(RadioButton) findViewById(R.id.radioNoNiniosnoescuela);
        niniosescuelano.setEnabled(vf);
        RadioButton alcoholismosi=(RadioButton) findViewById(R.id.radioSiAlcoholismo);
        alcoholismosi.setEnabled(vf);
        RadioButton alcoholismono=(RadioButton) findViewById(R.id.radioNoAlcoholismo);
        alcoholismono.setEnabled(vf);
        RadioButton drogassi=(RadioButton) findViewById(R.id.radioSiDrogas);
        drogassi.setEnabled(vf);
        RadioButton drogasno=(RadioButton) findViewById(R.id.radioNoDrogas);
        drogasno.setEnabled(vf);

        RadioButton cocinadormitoriosi=(RadioButton) findViewById(R.id.radioSiCocinaDormitorio);
        cocinadormitoriosi.setEnabled(vf);
        RadioButton cocinadormitoriono=(RadioButton) findViewById(R.id.radioNoCocinaDormitorio);
        cocinadormitoriono.setEnabled(vf);
        RadioButton sedazosi=(RadioButton) findViewById(R.id.radioSiSedazo);
        sedazosi.setEnabled(vf);
        RadioButton sedazono=(RadioButton) findViewById(R.id.radioNoSedazo);
        sedazono.setEnabled(vf);
        RadioButton mosquiterosi=(RadioButton) findViewById(R.id.radioSiMosquiteros);
        mosquiterosi.setEnabled(vf);
        RadioButton mosquiterono=(RadioButton) findViewById(R.id.radioNoMosquiteros);
        mosquiterono.setEnabled(vf);
        RadioButton plaguisidasi=(RadioButton) findViewById(R.id.radioSiPlaguisida);
        plaguisidasi.setEnabled(vf);
        RadioButton plaguisidano=(RadioButton) findViewById(R.id.radioNoPlaguisida);
        plaguisidano.setEnabled(vf);
        RadioButton aepisi=(RadioButton) findViewById(R.id.radioSiAEPI);
        aepisi.setEnabled(vf);
        RadioButton aepino=(RadioButton) findViewById(R.id.radioNoAEPI);
        aepino.setEnabled(vf);
        RadioButton abandonosi=(RadioButton) findViewById(R.id.radioSiAbandono);
        abandonosi.setEnabled(vf);
        RadioButton abandonono=(RadioButton) findViewById(R.id.radioNoAbandono);
        abandonono.setEnabled(vf);
        RadioButton irunidadsi=(RadioButton) findViewById(R.id.radioSiIrUnidad);
        irunidadsi.setEnabled(vf);
        RadioButton irunidadno=(RadioButton) findViewById(R.id.radioNoIrUnidad);
        irunidadno.setEnabled(vf);








        Button btbmiembrosfamilia= (Button) findViewById(R.id.miembrosHogar);
        btbmiembrosfamilia.setEnabled(vf);

    }


}



