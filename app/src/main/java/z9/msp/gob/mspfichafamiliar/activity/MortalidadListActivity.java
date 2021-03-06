package z9.msp.gob.mspfichafamiliar.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.Session;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContent;
import z9.msp.gob.mspfichafamiliar.activity.dummy.DummyContentMortalidad;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Mortalidad;
import z9.msp.gob.persistencia.entity.Parameters;
import z9.msp.gob.persistencia.entity.Personas;
import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;

/**
 * An activity representing a list of Personas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MortalidadListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static final String FORM_ID="FORM_ID";
    Session session;
    public String formularioId="";
    List<Mortalidad> mortalidadListList;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortalidad_list);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ACCION NUEVA PERSONA
                Context context = view.getContext();
                Intent intent = new Intent(context, MortalidadDetailActivity.class);
                intent.putExtra(MortalidadDetailFragment.ARG_ITEM_ID, "-1");
                context.startActivity(intent);
            }
        });
       View recyclerView = findViewById(R.id.mortalidad_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.mortalidad_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mortalidadListList=getMortalidadList();
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mortalidadListList));
    }
    private List<Mortalidad>  getMortalidadList(){
        formularioId=session.getFormulariosId();
        Cursor cursor=db.getAllDiscrimiator( new Parameters(TABLES.MORTALIDAD, CLS_DISCR.FORMULARIO_ID,formularioId));
        List<Mortalidad> mortalidadList=new ArrayList<>();
        String nombres,numCedula,fechaNac;
        int id=0;
        if(cursor.moveToNext()){
            do{
                nombres=cursor.getString(cursor.getColumnIndex("nombres"));
                numCedula=cursor.getString(cursor.getColumnIndex("cedula"));
                fechaNac=cursor.getString(cursor.getColumnIndex("fecha_muerte"));
                id=cursor.getInt(cursor.getColumnIndex("_id"));
                mortalidadList.add(new Mortalidad(nombres,numCedula,fechaNac,id));
            }while (cursor.moveToNext());
        }
        return  mortalidadList;
    }
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Mortalidad> mValues;

        public SimpleItemRecyclerViewAdapter(List<Mortalidad> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mortalidad_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder,final int position) {
            holder.mItem = mValues.get(position);
            holder.cedula.setText(mValues.get(position).getNumCedula());
            holder.nombres.setText(mValues.get(position).getNombres());
            holder.fechaNac.setText(mValues.get(position).getCausa());
            holder.foto.setImageResource(R.drawable.persona); //setImageResource();

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MortalidadDetailFragment.ARG_ITEM_ID, holder.mItem.getIdMortalida()+"");
                        MortalidadDetailFragment fragment = new MortalidadDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mortalidad_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MortalidadDetailActivity.class);
                        intent.putExtra(MortalidadDetailFragment.ARG_ITEM_ID, holder.mItem.getIdMortalida()+"");

                        context.startActivity(intent);
                    }
                }
            });
            holder.mView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    String id=holder.mItem.getIdMortalida()+"";
                    eliminarItem(id,v.getContext(),position);


                    return false;
                }
            });
        }
        private void eliminarItem(final String personaIds,final Context context,final int position){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("¿Desea eliminar el elemento seleccionado?");
            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    int i=db.deleteByKey(TABLES.PERSONAS,CLS_DISCR.ID,personaIds);
                    if(i>0){

                        AlertDialog.Builder goLogin = new AlertDialog.Builder(context);
                        goLogin.setMessage("Eliminacion correcta");
                        goLogin.setCancelable(false);
                        goLogin.setPositiveButton(S.aceptar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mValues.remove(position);
                                notifyItemRemoved(position);
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertLogin = goLogin.create();
                        alertLogin.show();
                    }

                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public Mortalidad mItem;
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
