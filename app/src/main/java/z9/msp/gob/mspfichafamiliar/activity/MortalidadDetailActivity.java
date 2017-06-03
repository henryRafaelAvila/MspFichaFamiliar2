package z9.msp.gob.mspfichafamiliar.activity;

import android.content.ContentValues;
import android.content.Context;
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
import z9.msp.gob.mspfichafamiliar.Session;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;
import z9.msp.gob.persistencia.utils.Utilitarios;

/**
 * An activity representing a single Persona detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonaListActivity}.
 */
public class MortalidadDetailActivity extends AppCompatActivity {
    DatabaseHandler db;
    String formularioId;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortalidad_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(this);
        session = new Session(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = db.getWs(WS.CATALOGO);
                String msj = saveOrUpdate();
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
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                formularioId = null;
            } else {
                formularioId = extras.getString(MortalidadListActivity.FORM_ID);
            }
        } else {
            formularioId = (String) savedInstanceState.getSerializable(MortalidadListActivity.FORM_ID);
        }
    }

    public String saveOrUpdate() {
        formularioId = session.getFormulariosId();
        ContentValues contentValues = valueViewPersonDetails();
        String msj = null;
//todo cambiar tipo de tabla a mortalidad
        String requiered[] = new String[]{"cedula", "apellidos", "nombres", "fecha_muerte", "causa"};
        for (String keyRequered : requiered) {
            Object valu = contentValues.get(keyRequered);
            if (valu == null || valu.equals("")) {
                msj = "Campo " + keyRequered + " es obligatorio";
                break;
            }
        }
        msj = Utilitarios.validarCedula(contentValues.get(S.keyCedula).toString());
        if (msj == null) {
            String id = db.existeObjectByCedulaAndFormID(contentValues.get(S.keyCedula).toString(), formularioId, TABLES.MORTALIDAD);
            String mortaldadId = getTexViewValue(R.id.id_mortalidad);
            boolean resultInsert = false;
            if (mortaldadId != null && mortaldadId.equals("-1")) {
                if (id == null) {
                    resultInsert = db.executeCreateQuery(contentValues, TABLES.MORTALIDAD);
                    msj = contentValues.get("nombres").toString() + " " + S.insertDato;
                } else {
                    msj = "El " + S.numCed + " ya existe";
                }
            } else {
                if (id.equals(mortaldadId)) {
                    int rows = db.updateById(TABLES.MORTALIDAD, mortaldadId, contentValues);
                    if (rows > 0) {
                        resultInsert = true;
                        msj = contentValues.get("nombres").toString() + " " + S.updateDato + " :Tot: " + rows;
                    }
                } else {
                    msj = "El " + S.numCed + " ya existe";
                }

            }
            if (resultInsert) {

                Context context = this;
                Intent intent = new Intent(context, MortalidadListActivity.class);
                intent.putExtra(MortalidadListActivity.FORM_ID, formularioId);
                context.startActivity(intent);
            }

        }
        return msj;
    }

    private String getTexViewValue(int editText) {
        TextView obj = (TextView) findViewById(editText);
        if (obj != null)
            return obj.getText().toString();
        else return null;
    }

    public ContentValues valueViewPersonDetails() {
        ContentValues values = new ContentValues();
        values.put(CLS_DISCR.FORMULARIO_ID.getColsName(), formularioId);
        values.put("cedula", getTextEditText(R.id.editTextCedula));
        values.put("apellidos", getTextEditText(R.id.editTextApellidos));
        values.put("nombres", getTextEditText(R.id.editTextnombres));
        values.put("id_par_jh", getValueSpinnerSelected(R.id.spinnerParentescoJefeHogar));
        String fechaMortalidad = ((TextView) findViewById(R.id.tv_fechaNac)).getText().toString();
        values.put("fecha_muerte", fechaMortalidad);
        int radioButtonID = ((RadioGroup) findViewById(R.id.opciones_sexo)).getCheckedRadioButtonId();
        if (radioButtonID == R.id.radioSexoHombre) {
            values.put("sexo", 1);
        } else {
            values.put("sexo", 2);
        }
        values.put("causa", getTextEditText(R.id.editTextCausa));
        return values;

    }

    private int getValueSpinnerSelected(int spinnerId) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        SQLiteCursor c = (SQLiteCursor) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
        String est = c.getString(c.getColumnIndex("_id"));
        return Integer.parseInt(est);
    }

    private String getTextEditText(int editText) {
        EditText obj = (EditText) findViewById(editText);
        if (obj != null)
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
