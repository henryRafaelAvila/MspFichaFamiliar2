package z9.msp.gob.mspfichafamiliar.activity;

import android.app.Activity;
import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContent;
import z9.msp.gob.persistencia.DatabaseHandler;


/**
 * A fragment representing a single Persona detail screen.
 * This fragment is either contained in a {@link PersonaListActivity}
 * in two-pane mode (on tablets) or a {@link PersonaDetailActivity}
 * on handsets.
 */
public class PersonaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    DatabaseHandler db;

    SimpleCursorAdapter adapterTipoResidente;
    Spinner spinnerTipoResidente;

    EditText editTextApellidos;
    EditText editTextnombres;
    EditText editTextCedula;
    EditText editTextEdad;
    EditText editTextOrdenNucleoFamiliar;
    EditText editTextPeso;
    EditText editTextTalla;

    Switch switchSeguroPrivado;
    Switch switchAsisteCDIP;
    Switch switchLeerScribe;
    Switch switchMatriculadoEstEnsReg;
    Switch switchAsistNormalmente;
    Switch switchVacunacionCompl;
    Switch switchControlOdont;
    Switch switchRecibLechMat;
    Switch switchAddSal;
    Switch switchDiscapacidadPerma;
    Switch switchCarnetConadis;
    Switch switchTieneHijos;
    
//TODO: FALTA ITEM PAR RECIBIR EL SEXO
    SimpleCursorAdapter adapterTieneDocumentacion;
    SimpleCursorAdapter adapterNacionalidad;
    SimpleCursorAdapter adapterEtnia;    
    SimpleCursorAdapter adapterSeguroPublico;
    SimpleCursorAdapter adapterParentescoJefeHogar;
    SimpleCursorAdapter adapterEstadoCivil;  
    SimpleCursorAdapter adapterParentescoJefeNucleoFamiliar;
    SimpleCursorAdapter adapterTipoDeCDIP;
    SimpleCursorAdapter adapterRazonNoMatrAnioLec;
    SimpleCursorAdapter adapterTipoEstab;
    SimpleCursorAdapter adapterNivelInstruccion;
    SimpleCursorAdapter adapterActSemPasada;
    SimpleCursorAdapter adapterRazonNoTrab;
    SimpleCursorAdapter adapterCatOcupacion;
    SimpleCursorAdapter adapterEstNutricional;
    SimpleCursorAdapter adapterEstNutrIMC;
    SimpleCursorAdapter adapterConsumoFrutVer;
    SimpleCursorAdapter adapterConsumoBebidAzu;
    SimpleCursorAdapter adapterConsumoCominaRap;
    SimpleCursorAdapter adapterConsumoSnacks;
    SimpleCursorAdapter adapterTipoSal;
    SimpleCursorAdapter adapterRiesgoNutri;
    SimpleCursorAdapter adapterRealizaActiFis;
    SimpleCursorAdapter adapterNivelActiFis;
    SimpleCursorAdapter adapterGradoDiscapacidad;


    Spinner spinnerTieneDocumentacion;
    Spinner spinnerNacionalidad;    
    Spinner spinnerEtnia;      
    Spinner spinnerSeguroPublico;
    Spinner spinnerParentescoJefeHogar;
    Spinner spinnerEstadoCivil;
    Spinner spinnerParentescoJefeNucleoFamiliar;
    Spinner spinnerTipoDeCDIP;
    Spinner spinnerRazonNoMatrAnioLec;
    Spinner spinnerTipoEstab;
    Spinner spinnerNivelInstruccion;
    Spinner spinnerActSemPasada;
    Spinner spinnerRazonNoTrab;
    Spinner spinnerCatOcupacion;
    Spinner spinnerEstNutricional;
    Spinner spinnerEstNutrIMC;
    Spinner spinnerConsumoFrutVer;
    Spinner spinnerConsumoBebidAzu;
    Spinner spinnerConsumoCominaRap;
    Spinner spinnerConsumoSnacks;
    Spinner spinnerTipoSal;
    Spinner spinnerRiesgoNutri;
    Spinner spinnerRealizaActiFis;
    Spinner spinnerNivelActiFis;
    Spinner spinnerGradoDiscapacidad;



    SimpleCursorAdapter adapterEmbarazoEnRiesgo;
    Spinner spinnerEmbarazoEnRiesgo;

    LinearLayout linerLayoutDetailMujer;

//
    RadioGroup radioGroupsexo;

    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(getContext());
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.persona_detail, container, false);

        // Show the dummy content as text in a TextView.
        spinnerTipoResidente = (Spinner)rootView.findViewById(R.id.spinnerTipoResidente);
        spinnerTieneDocumentacion= (Spinner)rootView.findViewById(R.id.spinnerTieneDocumentacion);
        spinnerGradoDiscapacidad= (Spinner)rootView.findViewById(R.id.spinnerGradoDiscapacidad);
        spinnerEmbarazoEnRiesgo= (Spinner)rootView.findViewById(R.id.spinnerEmbarazoEnRiesgo);
        spinnerNacionalidad= (Spinner)rootView.findViewById(R.id.spinnerNacionalidad);
        spinnerEtnia= (Spinner)rootView.findViewById(R.id.spinnerEtnia);
        spinnerSeguroPublico= (Spinner)rootView.findViewById(R.id.spinnerSeguroPublico);
        spinnerParentescoJefeHogar= (Spinner)rootView.findViewById(R.id.spinnerParentescoJefeHogar);
        spinnerEstadoCivil= (Spinner)rootView.findViewById(R.id.spinnerEstadoCivil);
        spinnerParentescoJefeNucleoFamiliar= (Spinner)rootView.findViewById(R.id.spinnerParentescoJefeNucleoFamiliar);
        spinnerTipoDeCDIP= (Spinner)rootView.findViewById(R.id.spinnerTipoDeCDIP);
        spinnerRazonNoMatrAnioLec= (Spinner)rootView.findViewById(R.id.spinnerRazonNoMatrAnioLec);
        spinnerTipoEstab= (Spinner)rootView.findViewById(R.id.spinnerTipoEstab);
        spinnerNivelInstruccion= (Spinner)rootView.findViewById(R.id.spinnerNivelInstruccion);
        spinnerActSemPasada= (Spinner)rootView.findViewById(R.id.spinnerActSemPasada);
        spinnerRazonNoTrab= (Spinner)rootView.findViewById(R.id.spinnerRazonNoTrab);
        spinnerCatOcupacion= (Spinner)rootView.findViewById(R.id.spinnerCatOcupacion);
        spinnerEstNutricional= (Spinner)rootView.findViewById(R.id.spinnerEstNutricional);
        spinnerEstNutrIMC= (Spinner)rootView.findViewById(R.id.spinnerEstNutrIMC);
        spinnerConsumoFrutVer= (Spinner)rootView.findViewById(R.id.spinnerConsumoFrutVer);
        spinnerConsumoBebidAzu= (Spinner)rootView.findViewById(R.id.spinnerConsumoBebidAzu);
        spinnerConsumoCominaRap= (Spinner)rootView.findViewById(R.id.spinnerConsumoCominaRap);
        spinnerConsumoSnacks= (Spinner)rootView.findViewById(R.id.spinnerConsumoSnacks);
        spinnerTipoSal= (Spinner)rootView.findViewById(R.id.spinnerTipoSal);
        spinnerRiesgoNutri= (Spinner)rootView.findViewById(R.id.spinnerRiesgoNutri);
        spinnerRealizaActiFis= (Spinner)rootView.findViewById(R.id.spinnerRealizaActiFis);
        spinnerNivelActiFis= (Spinner)rootView.findViewById(R.id.spinnerNivelActiFis);

        //activar u ocultar campos
        editTextEdad=(EditText) rootView.findViewById(R.id.editTextEdad);
        editTextEdad.addTextChangedListener(redoWatcher);
        //radioBurron
        radioGroupsexo=(RadioGroup) rootView.findViewById(R.id.opciones_sexo);
        linerLayoutDetailMujer=(LinearLayout) rootView.findViewById(R.id.activity_detail_mujer);

        radioGroupsexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                View radioButton = radioGroupsexo.findViewById(checkedId);
                switch (radioButton.getId()) {
                    case R.id.radioSexoHombre: // first button hombre

                        linerLayoutDetailMujer.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radioSexoMujer: // secondbutton mujer

                        linerLayoutDetailMujer.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.persona_detail)).setText(mItem.details);
            //llenada de combos
            pupulatedSpinner(adapterTipoResidente,db.getAllGeneric("recibe_agua"),spinnerTipoResidente);
            pupulatedSpinner(adapterTieneDocumentacion,db.getAllGeneric("recibe_agua"),spinnerTieneDocumentacion);
            pupulatedSpinner(adapterGradoDiscapacidad,db.getAllGeneric("recibe_agua"),spinnerGradoDiscapacidad);
            pupulatedSpinner(adapterEmbarazoEnRiesgo,db.getAllGeneric("recibe_agua"),spinnerEmbarazoEnRiesgo);
            pupulatedSpinner(adapterNacionalidad,db.getAllGeneric("recibe_agua"),spinnerNacionalidad);
            pupulatedSpinner(adapterEtnia,db.getAllGeneric("recibe_agua"),spinnerEtnia);
            pupulatedSpinner(adapterSeguroPublico,db.getAllGeneric("recibe_agua"),spinnerSeguroPublico);
            pupulatedSpinner(adapterParentescoJefeHogar,db.getAllGeneric("recibe_agua"),spinnerParentescoJefeHogar);
            pupulatedSpinner(adapterEstadoCivil,db.getAllGeneric("recibe_agua"),spinnerEstadoCivil);
            pupulatedSpinner(adapterParentescoJefeNucleoFamiliar,db.getAllGeneric("recibe_agua"),spinnerParentescoJefeNucleoFamiliar);
            pupulatedSpinner(adapterTipoDeCDIP,db.getAllGeneric("recibe_agua"),spinnerTipoDeCDIP);
            pupulatedSpinner(adapterRazonNoMatrAnioLec,db.getAllGeneric("recibe_agua"),spinnerRazonNoMatrAnioLec);
            pupulatedSpinner(adapterTipoEstab,db.getAllGeneric("recibe_agua"),spinnerTipoEstab);
            pupulatedSpinner(adapterNivelInstruccion,db.getAllGeneric("recibe_agua"),spinnerNivelInstruccion);
            pupulatedSpinner(adapterActSemPasada,db.getAllGeneric("recibe_agua"),spinnerActSemPasada);
            pupulatedSpinner(adapterRazonNoTrab,db.getAllGeneric("recibe_agua"),spinnerRazonNoTrab);
            pupulatedSpinner(adapterCatOcupacion,db.getAllGeneric("recibe_agua"),spinnerCatOcupacion);
            pupulatedSpinner(adapterEstNutricional,db.getAllGeneric("recibe_agua"),spinnerEstNutricional);
            pupulatedSpinner(adapterEstNutrIMC,db.getAllGeneric("recibe_agua"),spinnerEstNutrIMC);
            pupulatedSpinner(adapterConsumoFrutVer,db.getAllGeneric("recibe_agua"),spinnerConsumoFrutVer);
            pupulatedSpinner(adapterConsumoBebidAzu,db.getAllGeneric("recibe_agua"),spinnerConsumoBebidAzu);
            pupulatedSpinner(adapterConsumoCominaRap,db.getAllGeneric("recibe_agua"),spinnerConsumoCominaRap);
            pupulatedSpinner(adapterConsumoSnacks,db.getAllGeneric("recibe_agua"),spinnerConsumoSnacks);
            pupulatedSpinner(adapterTipoSal,db.getAllGeneric("recibe_agua"),spinnerTipoSal);
            pupulatedSpinner(adapterRiesgoNutri,db.getAllGeneric("recibe_agua"),spinnerRiesgoNutri);
            pupulatedSpinner(adapterRealizaActiFis,db.getAllGeneric("recibe_agua"),spinnerRealizaActiFis);
            pupulatedSpinner(adapterNivelActiFis,db.getAllGeneric("recibe_agua"),spinnerNivelActiFis);


        }

        return rootView;
    }
    private void pupulatedSpinner(SimpleCursorAdapter adapter, Cursor cursor,Spinner spinner){
        adapter = new SimpleCursorAdapter(getContext(),
                android.R.layout.simple_spinner_item,//Layout simple
                cursor,//Todos los registros
                new String[]{"descripcion"},//Mostrar solo el nombre
                new int[]{android.R.id.text1},//View para el nombre
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//Observer para el refresco
        spinner.setAdapter(adapter);
    }


    // listener
    private TextWatcher redoWatcher = new TextWatcher() {


        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")) {
                int edad = Integer.parseInt(s.toString());
                if (edad > 11) {
                    spinnerEstadoCivil.setVisibility(View.INVISIBLE);
                }else{
                    spinnerEstadoCivil.setVisibility(View.VISIBLE);
                }
            }

        }

    };
}
