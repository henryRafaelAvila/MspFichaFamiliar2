package z9.msp.gob.mspfichafamiliar.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import z9.msp.gob.mspfichafamiliar.MasterPageActivity;
import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.activity.FormularioDetalleActivity;
import z9.msp.gob.mspfichafamiliar.activity.NuevoFormularioActivity;
import z9.msp.gob.mspfichafamiliar.adapter.ListFormAdapter;
import z9.msp.gob.mspfichafamiliar.bdd.FormularioRepositorio;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Formulario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView mListForm;
    private ListFormAdapter mListFormAdapter;

    private OnFragmentInteractionListener mListener;
    DatabaseHandler db;

    public ListFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFormFragment newInstance(String param1, String param2) {
        ListFormFragment fragment = new ListFormFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this.getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void startActivityLocal(Class activity){
        Intent myIntent = new Intent(this.getContext(),activity);

        this.getContext().startActivity(myIntent);
    }
    private void eliminarFormularios(final Formulario currentFormulario){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage("¿Desea eliminar el formulario?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                int i=db.deleteFormularioById(currentFormulario.getIdFormulario()+"");
                if(i>0){

                    AlertDialog.Builder goLogin = new AlertDialog.Builder(getContext());
                    goLogin.setMessage("Eliminacion correcta");
                    goLogin.setCancelable(false);
                    goLogin.setPositiveButton(S.aceptar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivityLocal(MasterPageActivity.class);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_list_form, container, false);
        View root = inflater.inflate(R.layout.fragment_list_form, container, false);

        // Instancia del ListView.
        mListForm = (ListView) root.findViewById(R.id.form_list);

        // Inicializar el adaptador con la fuente de datos.
        mListFormAdapter =  new ListFormAdapter(getActivity(),db.getFormulariosList());

        //Relacionando la lista con el adaptador
        mListForm.setAdapter(mListFormAdapter);

        // Eventos
        mListForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Formulario currentFormulario = mListFormAdapter.getItem(position);
                Intent i = new Intent(getActivity(),NuevoFormularioActivity.class);
                i.putExtra(NuevoFormularioActivity.FORMULARIO_ID, currentFormulario.getIdFormulario()+"");
                startActivity(i);
            }
        });
mListForm.setOnItemLongClickListener((new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick (AdapterView<?> adapterView, View view, int position, long l) {
        Formulario currentFormulario = mListFormAdapter.getItem(position);
        eliminarFormularios(currentFormulario);
        return true;
    }
}));
        setHasOptionsMenu(true);
        return root;
    }
//sdfdsf
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
