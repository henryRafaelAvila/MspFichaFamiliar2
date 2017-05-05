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

    EditText  editTextCedula;

    SimpleCursorAdapter adapterNacionalidad;


    Spinner spinnerNacionalidad;


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
            mItem = DummyContentMortalidad.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

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
    //editTextCedula=(EditText) rootView.findViewById(R.id.editTextCedula);

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mortalidad_detail, container, false);
        //mostrarFechas(rootView);
        inicializarSpinner(rootView);
        initEditText(rootView);


        if (mItem != null) {
            populatedSpinner(mItem);
        }else{
            populatedSpinner();
        }


        return rootView;
    }
    private void inicializarSpinner(View rootView){
        // Show the dummy content as text in a TextView.
        spinnerNacionalidad= (Spinner)rootView.findViewById(R.id.spinnerMortalidad);
    }
    private void populatedSpinner(Mortalidad mItem) {
        //((TextView) rootView.findViewById(R.id.persona_detail)).setText(mItem.details);
        //llenada de combos
        populatedSpinner();

    }
    private void populatedSpinner() {
        //llenada de combos
        pupulatedSpinner(adapterNacionalidad,db.getAllGeneric(TABLES.NACIONALIDAD.getTablaName()),spinnerNacionalidad);
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
