package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import z9.msp.gob.mspfichafamiliar.R;

import z9.msp.gob.mspfichafamiliar.Session;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContent;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Parameters;
import z9.msp.gob.persistencia.entity.Personas;
import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Personas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PersonaListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static final String FORM_ID="FORM_ID";
    public String formularioId="";
    DatabaseHandler db;
   Session session;
    List<Personas> personasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_list);
        session=new Session(this);
        db = new DatabaseHandler(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                formularioId=session.getFormulariosId();
            } else {
                formularioId= extras.getString(FORM_ID);
                session.setFormulariosId(formularioId);
            }
        } else {
            formularioId= (String) savedInstanceState.getSerializable(FORM_ID);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ACCION NUEVA PERSONA
                Context context = view.getContext();
                Intent intent = new Intent(context, PersonaDetailActivity.class);
                intent.putExtra(PersonaDetailFragment.ARG_ITEM_ID, "-1");
                intent.putExtra(FORM_ID,formularioId);
                context.startActivity(intent);
            }
        });
       View recyclerView = findViewById(R.id.persona_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.persona_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        personasList=getPersonasList();
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(personasList));
    }
    private List<Personas>  getPersonasList(){
        formularioId=session.getFormulariosId();
        Cursor cursor=db.getAllDiscrimiator( new Parameters(TABLES.PERSONAS, CLS_DISCR.FORMULARIO_ID,formularioId));
        List<Personas> personasList=new ArrayList<>();
        String nombres,numCedula,fechaNac;
        int id=0;
        if(cursor.moveToNext()){
            do{
                nombres=cursor.getString(cursor.getColumnIndex("nombres"));
                numCedula=cursor.getString(cursor.getColumnIndex("cedula"));
                fechaNac=cursor.getString(cursor.getColumnIndex("fecha_nac"));
                id=cursor.getInt(cursor.getColumnIndex("_id"));
                personasList.add(new Personas(nombres,numCedula,fechaNac,id));
            }while (cursor.moveToNext());
        }
        return  personasList;
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Personas> mValues;

        public SimpleItemRecyclerViewAdapter(List<Personas> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.persona_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.cedula.setText(mValues.get(position).getNumCedula());
            holder.nombres.setText(mValues.get(position).getNombres());
            holder.fechaNac.setText(mValues.get(position).getFechaNac());
            holder.foto.setImageResource(R.drawable.persona); //setImageResource();

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PersonaDetailFragment.ARG_ITEM_ID, holder.mItem.getIdPersona()+"");
                        PersonaDetailFragment fragment = new PersonaDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.persona_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PersonaDetailActivity.class);
                        intent.putExtra(PersonaDetailFragment.ARG_ITEM_ID, holder.mItem.getIdPersona()+"");

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public Personas mItem;
            // public final TextView mIdView;
            public final TextView nombres;
            public final TextView cedula;
            public final TextView fechaNac;
            public final ImageView  foto;


            public ViewHolder(View view) {
                super(view);
                mView = view;

                nombres=(TextView) view.findViewById(R.id.tv_nombre);
                cedula=(TextView) view.findViewById(R.id.tv_numCedula);
                fechaNac=(TextView) view.findViewById(R.id.tv_fechaNac);
                foto=(ImageView) view.findViewById(R.id.iv_foto);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + nombres.getText() + "'";
            }
        }
    }
}
