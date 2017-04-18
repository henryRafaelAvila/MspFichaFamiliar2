package z9.msp.gob.mspfichafamiliar.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import java.util.Calendar;

import android.icu.util.Output;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import z9.msp.gob.persistencia.entity.Personas;


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

    EditText editTextEdad;
    
//TODO: FALTA ITEM PAR RECIBIR EL SEXO
    SimpleCursorAdapter adapterNacionalidad;
    SimpleCursorAdapter adapterEtnia;    
    SimpleCursorAdapter adapterSeguroPublico;
    SimpleCursorAdapter adapterParentescoJefeHogar;
    SimpleCursorAdapter adapterEstadoCivil;
    SimpleCursorAdapter adapterNivelInstruccion;


    Spinner spinnerNacionalidad;    
    Spinner spinnerEtnia;      
    Spinner spinnerSeguroPublico;
    Spinner spinnerParentescoJefeHogar;
    Spinner spinnerEstadoCivil;
    Spinner spinnerNivelInstruccion;

    LinearLayout linerLayoutDetailMujer;
    RadioGroup radioGroupsexo;
    /*FECHA DE NACIMIENTO*/
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView tv_fechaNac;
    private Button selectFechaNacimiento;
    DatePickerDialog datePickerDialog;


    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Personas mItem;

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
                if(mItem!=null&&mItem.getNombres()!=null) {
                    appBarLayout.setTitle(mItem.getNombres());
                }else{
                    appBarLayout.setTitle("Nueva Persona");
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.persona_detail, container, false);
        //mostrarFechas(rootView);
        inicializarSpinner(rootView);

        //activar u ocultar campos
        editTextEdad=(EditText) rootView.findViewById(R.id.editTextEdad);
        //editTextEdad.addTextChangedListener(redoWatcher);
        //fechaNac
        tv_fechaNac=(TextView) rootView.findViewById(R.id.tv_fechaNac);
        selectFechaNacimiento=(Button) rootView.findViewById(R.id.selectFechaNacimiento);
        selectFechaNacimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int mYear,mMonth,mDay;
                if(tv_fechaNac.getText().equals("")){
                    mYear = c.get(Calendar.YEAR); // current year
                    mMonth = c.get(Calendar.MONTH); // current month
                    mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                }else{
                    String date[]=tv_fechaNac.getText().toString().split("/");
                    mYear = Integer.parseInt(date[2]);
                    mMonth = Integer.parseInt(date[1])-1;
                    mDay = Integer.parseInt(date[0]);
                }

                // date picker dialog
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                tv_fechaNac.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                                String text=(c.get(Calendar.YEAR)-year)+ " años "+Math.abs(c.get(Calendar.MONTH)-(monthOfYear-1))+" meses";
                                editTextEdad.setText(text);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }

        });
        //radioBurron
        radioGroupsexo=(RadioGroup) rootView.findViewById(R.id.opciones_sexo);


        radioGroupsexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                View radioButton = radioGroupsexo.findViewById(checkedId);
                //radioButton.getId()// id selected
            }
        });

        if (mItem != null) {
            populatedSpinner(mItem);
        }else{
            populatedSpinner();
        }

        return rootView;
    }
    private void inicializarSpinner(View rootView){
        // Show the dummy content as text in a TextView.
        spinnerNacionalidad= (Spinner)rootView.findViewById(R.id.spinnerNacionalidad);
        spinnerEtnia= (Spinner)rootView.findViewById(R.id.spinnerEtnia);
        spinnerSeguroPublico= (Spinner)rootView.findViewById(R.id.spinnerSeguroPublico);
        spinnerParentescoJefeHogar= (Spinner)rootView.findViewById(R.id.spinnerParentescoJefeHogar);
        spinnerEstadoCivil= (Spinner)rootView.findViewById(R.id.spinnerEstadoCivil);
        spinnerNivelInstruccion= (Spinner)rootView.findViewById(R.id.spinnerNivelInstruccion);
    }
    private void populatedSpinner(Personas mItem) {
        //((TextView) rootView.findViewById(R.id.persona_detail)).setText(mItem.details);
        //llenada de combos
        pupulatedSpinner(adapterNacionalidad,db.getAllGeneric("recibe_agua"),spinnerNacionalidad);
        pupulatedSpinner(adapterEtnia,db.getAllGeneric("recibe_agua"),spinnerEtnia);
        pupulatedSpinner(adapterSeguroPublico,db.getAllGeneric("recibe_agua"),spinnerSeguroPublico);
        pupulatedSpinner(adapterParentescoJefeHogar,db.getAllGeneric("recibe_agua"),spinnerParentescoJefeHogar);
        pupulatedSpinner(adapterEstadoCivil,db.getAllGeneric("recibe_agua"),spinnerEstadoCivil);
        pupulatedSpinner(adapterNivelInstruccion,db.getAllGeneric("recibe_agua"),spinnerNivelInstruccion);
    }
    private void populatedSpinner() {
        //((TextView) rootView.findViewById(R.id.persona_detail)).setText(mItem.details);
        //llenada de combos
        pupulatedSpinner(adapterNacionalidad,db.getAllGeneric("recibe_agua"),spinnerNacionalidad);
        pupulatedSpinner(adapterEtnia,db.getAllGeneric("recibe_agua"),spinnerEtnia);
        pupulatedSpinner(adapterSeguroPublico,db.getAllGeneric("recibe_agua"),spinnerSeguroPublico);
        pupulatedSpinner(adapterParentescoJefeHogar,db.getAllGeneric("recibe_agua"),spinnerParentescoJefeHogar);
        pupulatedSpinner(adapterEstadoCivil,db.getAllGeneric("recibe_agua"),spinnerEstadoCivil);
        pupulatedSpinner(adapterNivelInstruccion,db.getAllGeneric("recibe_agua"),spinnerNivelInstruccion);
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
//    private TextWatcher redoWatcher = new TextWatcher() {
//
//
//        public void beforeTextChanged(CharSequence s, int start, int count,
//                                      int after) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before,
//                                  int count) {
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if(!s.toString().equals("")) {
//                int edad = Integer.parseInt(s.toString());
//                if (edad > 11) {
//                    spinnerEstadoCivil.setVisibility(View.INVISIBLE);
//                }else{
//                    spinnerEstadoCivil.setVisibility(View.VISIBLE);
//                }
//            }
//
//        }
//
//    };

}
