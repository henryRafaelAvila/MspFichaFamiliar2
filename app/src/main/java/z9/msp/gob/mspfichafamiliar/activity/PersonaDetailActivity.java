package z9.msp.gob.mspfichafamiliar.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.Session;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;

/**
 * An activity representing a single Persona detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonaListActivity}.
 */
public class PersonaDetailActivity extends AppCompatActivity {
    DatabaseHandler db;
    String formularioId;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(this);
        session=new Session(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj=saveOrUpdate();
                Snackbar.make(view, msj, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(PersonaDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PersonaDetailFragment.ARG_ITEM_ID));
            PersonaDetailFragment fragment = new PersonaDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.persona_detail_container, fragment)
                    .commit();
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                formularioId= null;
            } else {
                formularioId= extras.getString(PersonaListActivity.FORM_ID);
            }
        } else {
            formularioId= (String) savedInstanceState.getSerializable(PersonaListActivity.FORM_ID);
        }


    }
    public String saveOrUpdate(){
        formularioId=session.getFormulariosId();
        ContentValues contentValues=valueViewPersonDetails();
        String msj=null;
    String personaId=getTexViewValue(R.id.id_persona);
        boolean resultInsert=false;
        if(personaId!=null&&personaId.equals("-1")) {
            resultInsert= db.executeCreateQuery(contentValues, TABLES.PERSONAS);
            msj=contentValues.get("nombres").toString()+" "+S.insertDato;
        }else{
            int rows=db.updateById(TABLES.PERSONAS,personaId,contentValues);
            if(rows>0){
            resultInsert=true;
                msj=contentValues.get("nombres").toString()+" "+S.updateDato +" :Tot: "+rows;
            }
        }
        if(resultInsert){

            Context context = this;
            Intent intent = new Intent(context, PersonaListActivity.class);
            intent.putExtra(PersonaListActivity.FORM_ID, formularioId);
            context.startActivity(intent);
        }
        return msj;
    }
    public ContentValues valueViewPersonDetails(){
        ContentValues values=new ContentValues();
        values.put(CLS_DISCR.FORMULARIO_ID.getColsName(),formularioId);
        values.put("cedula",getTextEditText(R.id.editTextCedula));
        values.put("cedula",getTextEditText(R.id.editTextCedula));
        values.put("apellidos",getTextEditText(R.id.editTextApellidos));
        values.put("nombres",getTextEditText(R.id.editTextnombres));
        values.put("id_par_jh",getValueSpinnerSelected(R.id.spinnerParentescoJefeHogar));

        // todo sexo radiobutton groud
        String fechaNac=((TextView)findViewById(R.id.tv_fechaNac)).getText().toString();
        values.put("fecha_nac",fechaNac);
        int radioButtonID = ((RadioGroup)findViewById(R.id.opciones_sexo)).getCheckedRadioButtonId();
        if(radioButtonID ==R.id.radioSexoHombre){
            values.put("sexo",1);
        }else{
            values.put("sexo",2);
        }

        values.put("id_est_civ",getValueSpinnerSelected(R.id.spinnerEstadoCivil));
        values.put("id_act_trab",getValueSpinnerSelected(R.id.spinnerActSemPasada));
        values.put("id_niv_inst",getValueSpinnerSelected(R.id.spinnerNivelInstruccion));
        values.put("id_etn",getValueSpinnerSelected(R.id.spinnerEtnia));

        boolean tieneSeguroPrivado;
        String etniaCod=getValueSpinnerSelected(R.id.spinnerEtnia,"cod_etn");
        if(etniaCod.equals("1")){//indigena
            values.put("id_nacs",getValueSpinnerSelected(R.id.spinnerNacionalidades));

            String cod_nacs=getValueSpinnerSelected(R.id.spinnerNacionalidades,"cod_nacs");
            if(cod_nacs.equals("7"))//achuar
            {
                values.put("id_pue",getValueSpinnerSelected(R.id.spinnerPueblos));
            }
        }

        values.put("id_nac",getValueSpinnerSelected(R.id.spinnerNacionalidad));
        values.put("id_seg_pub",getValueSpinnerSelected(R.id.spinnerSeguroPublico));
        values.put("id_claf_diag",getValueSpinnerSelected(R.id.spinnerClasificacionDiagnost));
        final Calendar c = Calendar.getInstance();
        String fechaVisiata=c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
        values.put("fecha_diag",fechaVisiata);//si

        tieneSeguroPrivado=((Switch)findViewById(R.id.switchSeguroPrivado)).isChecked();
        if(tieneSeguroPrivado){
            values.put("seguro_priv",1);//si
            values.put("det_seg_privado",getTextEditText(R.id.editTextSeguroPrivado));
        }else{
            values.put("seguro_priv",2);//no
        }
        return values;

    }
    private String getValueSpinnerSelected(int spinnerId,String cols){
        String value;
        Spinner spinner=(Spinner) findViewById(spinnerId);
        SQLiteCursor c=(SQLiteCursor)spinner.getItemAtPosition(spinner.getSelectedItemPosition());
        value=c.getString(c.getColumnIndex(cols));
        return value;
    }
    private int getValueSpinnerSelected(int spinnerId){
        Spinner spinner=(Spinner) findViewById(spinnerId);
        SQLiteCursor c=(SQLiteCursor)spinner.getItemAtPosition(spinner.getSelectedItemPosition());
        String est=c.getString(c.getColumnIndex("_id"));
        return Integer.parseInt(est);
    }
    private String getTextEditText(int editText){
        EditText obj=(EditText) findViewById(editText);
       if(obj!=null)
           return obj.getText().toString();
        else return null;
    }
    private String getTexViewValue(int editText){
        TextView obj=(TextView) findViewById(editText);
        if(obj!=null)
            return obj.getText().toString();
        else return null;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //

            navigateUpTo(new Intent(this, PersonaListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
