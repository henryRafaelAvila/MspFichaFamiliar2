package z9.msp.gob.mspfichafamiliar.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContentMortalidad;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Mortalidad;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.utils.Utilitarios;


/**
 * A fragment representing a single Persona detail screen.
 * This fragment is either contained in a {@link PersonaListActivity}
 * in two-pane mode (on tablets) or a {@link PersonaDetailActivity}
 * on handsets.
 */
public class MortalidadDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    DatabaseHandler db;


    SimpleCursorAdapter adapterCausaMortalidad;
    SimpleCursorAdapter adapterParentescoJefeHogar;

    Spinner spinnerCausaMortalidad;
    Spinner spinnerParentescoJefeHogar;
    EditText  editTextCedula;
    EditText editTextApellidos;
    EditText editTextnombres;
    EditText editTextEdad;
    EditText  editTextCausa;
    private TextView tv_fechaNac;
    RadioGroup radioGroupsexo;
    private TextView tvMortalidadId;
    private Button selectFechaNacimiento;
    DatePickerDialog datePickerDialog;


    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Mortalidad mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MortalidadDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(getContext());
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = db.getMortalidadById(getArguments().getString(ARG_ITEM_ID));

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
    editTextCausa=(EditText) rootView.findViewById(R.id.editTextCausa);
    tvMortalidadId=(TextView) rootView.findViewById(R.id.id_mortalidad);

}
    private  void setValuesTextEdit(Mortalidad mItem){
        editTextCedula.setText(mItem.getNumCedula());
        editTextApellidos.setText(mItem.getApellidos());
        editTextnombres.setText(mItem.getNombres());
        editTextCausa.setText(mItem.getCausa());
        tv_fechaNac.setText(Utilitarios.dateToString(mItem.getFechaMuerte()));
        tvMortalidadId.setText(mItem.getIdMortalida()+"");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mortalidad_detail, container, false);
        //mostrarFechas(rootView);
        inicializarSpinner(rootView);
        initEditText(rootView);
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

        if (mItem != null) {
            populatedSpinner(mItem);
        }else{
            populatedSpinner();
        }


        return rootView;
    }
    private void inicializarSpinner(View rootView){
        // Show the dummy content as text in a TextView.
        spinnerCausaMortalidad= (Spinner)rootView.findViewById(R.id.spinnerCausaMortalidad);
        spinnerParentescoJefeHogar= (Spinner)rootView.findViewById(R.id.spinnerParentescoJefeHogar);
    }
    private void populatedSpinner(Mortalidad mItem) {
        //((TextView) rootView.findViewById(R.id.persona_detail)).setText(mItem.details);
        //llenada de combos
        populatedSpinner();
        setValuesTextEdit(mItem);
        spinnerParentescoJefeHogar.setSelection(Utilitarios.getPosition(spinnerParentescoJefeHogar,mItem.getIdParJh()));
        spinnerCausaMortalidad.setSelection(Utilitarios.getPosition(spinnerCausaMortalidad,mItem.getIdCauMor()));

    }
    private void populatedSpinner() {
        //llenada de combos
        pupulatedSpinner(adapterCausaMortalidad,db.getAllGeneric(TABLES.CAUSA_MOTALIDAD.getTablaName()),spinnerCausaMortalidad);
        pupulatedSpinner(adapterParentescoJefeHogar,db.getAllGeneric(TABLES.PARENT_JE_HO.getTablaName()),spinnerParentescoJefeHogar);
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
