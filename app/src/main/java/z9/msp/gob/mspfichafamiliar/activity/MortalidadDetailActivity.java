package z9.msp.gob.mspfichafamiliar.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;

/**
 * An activity representing a single Persona detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonaListActivity}.
 */
public class MortalidadDetailActivity extends AppCompatActivity {
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortalidad_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=db.getWs(WS.CATALOGO);
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

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MortalidadDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(MortalidadDetailFragment.ARG_ITEM_ID));
            MortalidadDetailFragment fragment = new MortalidadDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mortalidad_detail_container, fragment)
                    .commit();
        }
    }
    public String saveOrUpdate(){
        ContentValues contentValues=valueViewPersonDetails();
        String msj=null;
//todo cambiar tipo de tabla a mortalidad
              boolean resultInsert= db.executeCreateQuery(contentValues, TABLES.MATERIAL_PARED);
        if(resultInsert){
            msj=contentValues.get("nombres").toString()+" "+S.insertDato;
        }
        return msj;
    }
    public ContentValues valueViewPersonDetails(){
        ContentValues values=new ContentValues();
       // values.put("cedula",getTextEditText(R.id.editTextCedula));
       // values.put("apellidos",getTextEditText(R.id.editTextApellidos));

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MortalidadListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}