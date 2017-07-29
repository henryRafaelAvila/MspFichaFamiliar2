package z9.msp.gob.mspfichafamiliar.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.MainActivity;
import z9.msp.gob.mspfichafamiliar.MasterPageActivity;
import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.mspfichafamiliar.utilsApp.RestClient;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Formulario;
import z9.msp.gob.persistencia.enums.WS;

import static android.R.attr.value;

public class DescargarFormularios extends AppCompatActivity {
    ProgressDialog progress;
    DatabaseHandler db;
    private List<String> msj;
    EditText numForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar_formularios);
        db = new DatabaseHandler(this);
        progress = new ProgressDialog(DescargarFormularios.this);
        Button dowload = (Button) findViewById(R.id.btn_download_form);
        numForm = (EditText) findViewById(R.id.et_num_formularios);
        ;
        dowload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String idsFormulario = numForm.getText().toString();//todo: implementar seleccion de datos
                try {
                    if (idsFormulario.contains("-")) {

                        String list[] = idsFormulario.split("\\-");
                        int ini = Integer.parseInt(list[0]);
                        int fin = Integer.parseInt(list[1]);
                        idsFormulario = "";
                        for (int i = ini; i <= fin; i++) {
                            idsFormulario = idsFormulario + i + ",";
                        }
                        idsFormulario = idsFormulario.substring(0, idsFormulario.length() - 1);

                }else{
                    String list[] = idsFormulario.split(",");
                    for (int i = 0; i <list.length; i++) {
                        Integer.parseInt(list[i]);
                    }
                }
                } catch (RuntimeException e) {
                    idsFormulario=null;
                    showMessa("Formato incorrecto para descargar varios formularios. ejemplo: 1-10");
                }
                if(idsFormulario!=null&&!idsFormulario.equals("")) {
                    progress.setMessage(S.establecerConeccion);
                    progress.show();
                    String url = db.getWs(WS.DESCARGA_FORMULARIOS);
                    progress.setCancelable(false);
                    new GetFormularios(progress,DescargarFormularios.this,url,idsFormulario).execute();
                }
            }

        });
    }

    private void showMessa(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    /*
La clase GetCommentsTask representa una tarea asincrona que realizará
las operaciones de red necesarias en segundo plano para obtener toda la
lista de comentarios alojada en el servidor.
*/
    public class GetFormularios extends AsyncTask<String, Void, List<String>> {
        ProgressDialog progress;
        Context context;
        String url;
        String idsFormulario;

        public GetFormularios(ProgressDialog progress, Context act, String url, String idsFormulario) {
            this.progress = progress;
            this.context = act;
            this.url = url;
            this.idsFormulario = idsFormulario;
        }

        public void onPreExecute() {
            progress.show();

        }

        @Override
        protected List<String> doInBackground(String... urls) {

            List<String> comments = new ArrayList<String>();
            try {

                // Establecer la conexión

                RestClient client = new RestClient(url);
                client.AddParam(S.dw_idsFormularios, idsFormulario);
                client.Execute(RestClient.RequestMethod.GET);
                String response = client.getResponse();
                if (response != null && !response.equals("")) {
                    Type listType = new TypeToken<List<Formulario>>() {
                    }.getType();
                    List<Formulario> lista = new GsonBuilder()
                            .setDateFormat("dd/MM/yyyy")
                            .create()
                            .fromJson(response, listType);
                    String urlConfirm = db.getWs(WS.CONFIRM_DOWNLOAD);
                    RestClient clientConfirm = new RestClient(urlConfirm);
                    int n=-1;
                    for (Formulario formulario : lista) {
                        n=db.insertFormularioByWs(formulario);
                        if(n>0) {
                            clientConfirm.AddParam(S.dw_idsFormulariosConfirm, formulario.getIdFormulario()+"");
                            clientConfirm.Execute(RestClient.RequestMethod.GET);
                        }

                    }
                } else {
                    comments.add(S.dw_NotData);
                }


            } catch (Exception e) {
                comments.add(S.errorServer + " : " + e.getMessage());


            }
            comments.add(S.successServer);

            return comments;

        }

        @Override
        protected void onPostExecute(List<String> s) {
            progress.dismiss();
            AlertDialog.Builder goLogin = new AlertDialog.Builder(context);
            goLogin.setMessage(s.get(0));
            goLogin.setCancelable(false);
            goLogin.setPositiveButton(S.aceptar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertLogin = goLogin.create();
            alertLogin.show();


        }
    }

}
