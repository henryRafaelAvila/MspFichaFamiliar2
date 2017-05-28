package z9.msp.gob.mspfichafamiliar.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import java.util.Calendar;

import android.database.sqlite.SQLiteCursor;
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
import android.widget.AdapterView;
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
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContent;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Personas;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.utils.Utilitarios;


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

    EditText  editTextCedula;
    EditText editTextApellidos;
    EditText editTextnombres;
    EditText editTextEdad;
    EditText editTextSeguroPrivado;
    
//TODO: FALTA ITEM PAR RECIBIR EL SEXO
    SimpleCursorAdapter adapterNacionalidad;
    SimpleCursorAdapter adapterEtnia;    
    SimpleCursorAdapter adapterSeguroPublico;
    SimpleCursorAdapter adapterParentescoJefeHogar;
    SimpleCursorAdapter adapterEstadoCivil;
    SimpleCursorAdapter adapterNivelInstruccion;
    SimpleCursorAdapter adapterActSemPasada;
    SimpleCursorAdapter adapterNacionalidades;
    SimpleCursorAdapter adapterPueblos;
    SimpleCursorAdapter adapterClasificacionDiagnost;


    Spinner spinnerNacionalidad;    
    Spinner spinnerEtnia;      
    Spinner spinnerSeguroPublico;
    Spinner spinnerParentescoJefeHogar;
    Spinner spinnerEstadoCivil;
    Spinner spinnerNivelInstruccion;
    Spinner  spinnerActSemPasada;
    Spinner spinnerNacionalidades;
    Spinner spinnerPueblos;
    Spinner spinnerClasificacionDiagnost;
    RadioGroup radioGroupsexo;
    /*FECHA DE NACIMIENTO*/
    private TextView tv_fechaNac;
    private TextView tvNacionalidad;
    private TextView tvPueblos;
    private TextView tvPersonaId;
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
            mItem = db.getPersonaById(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                if(mItem!=null&&mItem.getNombres()!=null) {
                    appBarLayout.setTitle(mItem.getNombres());
                }else{
                    appBarLayout.setTitle(S.nuevaPersona);
                }
            }
        }
    }



private void initEditText(View rootView){
    editTextCedula=(EditText) rootView.findViewById(R.id.editTextCedula);
    editTextApellidos=(EditText) rootView.findViewById(R.id.editTextApellidos);
    editTextnombres=(EditText) rootView.findViewById(R.id.editTextnombres);
    editTextEdad=(EditText) rootView.findViewById(R.id.editTextEdad);
    editTextSeguroPrivado=(EditText) rootView.findViewById(R.id.editTextSeguroPrivado);
    tvPersonaId=(TextView) rootView.findViewById(R.id.id_persona);

}
    private  void setValuesTextEdit(Personas mItem){
        editTextCedula.setText(mItem.getNumCedula());
        editTextApellidos.setText(mItem.getApellidos());
        editTextnombres.setText(mItem.getNombres());
        editTextSeguroPrivado.setText(mItem.getDetSegPrivado());
        tv_fechaNac.setText(Utilitarios.dateToString(mItem.getFechaNac()));
        tvPersonaId.setText(mItem.getIdPersona()+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.persona_detail, container, false);
        //mostrarFechas(rootView);
        inicializarSpinner(rootView);
        initEditText(rootView);

        tv_fechaNac=(TextView) rootView.findViewById(R.id.tv_fechaNac);
        tvNacionalidad=(TextView) rootView.findViewById(R.id.tvNacionalidad);
        tvPueblos=(TextView) rootView.findViewById(R.id.tvPueblos);
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
//DESPUES DE LLENAR LOS SPPINER PASO A ACTIVAR EVENTES DEPENDIENDO DEL TIPO DE ITEM SE MUESTRA U OCULTA PREGRUNTA
        //EJPM : PREGUNTA COMO SE AUTOIDENTIFICA
        spinnerEtnia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                Object item=parentView.getItemAtPosition(position);
                String value = String.valueOf(((SQLiteCursor)item).getString(1));
                if(value.equals("1")){//indigena true
                    //show
                    tvNacionalidad.setVisibility(View.VISIBLE);
                    spinnerNacionalidades.setVisibility(View.VISIBLE);//show nacionaleses
                }else{
                    //hide
                    tvNacionalidad.setVisibility(View.GONE);
                    spinnerNacionalidades.setVisibility(View.GONE);
                    tvPueblos.setVisibility(View.GONE);
                    spinnerPueblos.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinnerNacionalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                Object item=parentView.getItemAtPosition(position);
                String value = String.valueOf(((SQLiteCursor)item).getString(1));
                    if(value.equals("7")){//indigena true
                        //show
                        tvPueblos.setVisibility(View.VISIBLE);
                        spinnerPueblos.setVisibility(View.VISIBLE);
                    }else{
                        //hide
                        tvPueblos.setVisibility(View.GONE);
                        spinnerPueblos.setVisibility(View.GONE);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return rootView;
    }
    private void inicializarSpinner(View rootView){
        spinnerNacionalidad= (Spinner)rootView.findViewById(R.id.spinnerNacionalidad);
        spinnerEtnia= (Spinner)rootView.findViewById(R.id.spinnerEtnia);
        spinnerSeguroPublico= (Spinner)rootView.findViewById(R.id.spinnerSeguroPublico);
        spinnerParentescoJefeHogar= (Spinner)rootView.findViewById(R.id.spinnerParentescoJefeHogar);
        spinnerEstadoCivil= (Spinner)rootView.findViewById(R.id.spinnerEstadoCivil);
        spinnerNivelInstruccion= (Spinner)rootView.findViewById(R.id.spinnerNivelInstruccion);
        spinnerActSemPasada= (Spinner)rootView.findViewById(R.id.spinnerActSemPasada);
        spinnerNacionalidades= (Spinner)rootView.findViewById(R.id.spinnerNacionalidades);
        spinnerPueblos= (Spinner)rootView.findViewById(R.id.spinnerPueblos);
        spinnerClasificacionDiagnost= (Spinner)rootView.findViewById(R.id.spinnerClasificacionDiagnost);
    }
    private void populatedSpinner(Personas mItema) {
        populatedSpinner();
        setValuesTextEdit(mItem);
       /* spinnerNacionalidad.setSelection(mItem.getIdNac());
        spinnerEtnia.setSelection(mItem.getIdEtn());
        spinnerSeguroPublico.setSelection(mItem.getIdSegPub());
        spinnerParentescoJefeHogar.setSelection(mItem.getIdParJh());
        spinnerEstadoCivil.setSelection(mItem.getIdEstCiv());
        spinnerNivelInstruccion.setSelection(mItem.getIdNivInst());
        spinnerActSemPasada.setSelection(mItem.getIdActTrab());
        spinnerNacionalidades.setSelection(mItem.getIdNacs());
        spinnerPueblos.setSelection(mItem.getIdPue());
        spinnerClasificacionDiagnost.setSelection(mItem.getIdClafDiag());*/

    }
    private void populatedSpinner() {
        //llenada de combos
        pupulatedSpinner(adapterNacionalidad,db.getAllGeneric(TABLES.NACIONALIDAD.getTablaName()),spinnerNacionalidad);
        pupulatedSpinner(adapterEtnia,db.getAllGeneric(TABLES.ETNIA.getTablaName()),spinnerEtnia);
        pupulatedSpinner(adapterSeguroPublico,db.getAllGeneric(TABLES.SEGPUBLICO.getTablaName()),spinnerSeguroPublico);
        pupulatedSpinner(adapterParentescoJefeHogar,db.getAllGeneric(TABLES.PARENT_JE_HO.getTablaName()),spinnerParentescoJefeHogar);
        pupulatedSpinner(adapterEstadoCivil,db.getAllGeneric(TABLES.ESTADO_CIVIL.getTablaName()),spinnerEstadoCivil);
        pupulatedSpinner(adapterNivelInstruccion,db.getAllGeneric(TABLES.NIVEL_INSTRS.getTablaName()),spinnerNivelInstruccion);
        pupulatedSpinner(adapterActSemPasada,db.getAllGeneric(TABLES.ACTIVIADAD_TRABAJO.getTablaName()),spinnerActSemPasada);
        pupulatedSpinner(adapterNacionalidades,db.getAllGeneric(TABLES.NACIONALIDADES.getTablaName()),spinnerNacionalidades);
        pupulatedSpinner(adapterPueblos,db.getAllGeneric(TABLES.PUEBLOS.getTablaName()),spinnerPueblos);
        pupulatedSpinner(adapterClasificacionDiagnost,db.getAllGeneric(TABLES.CLASF_DIAGNOS.getTablaName()),spinnerClasificacionDiagnost);
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

}
