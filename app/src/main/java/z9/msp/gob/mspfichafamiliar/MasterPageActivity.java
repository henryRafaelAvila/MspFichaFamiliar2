package z9.msp.gob.mspfichafamiliar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import z9.msp.gob.mspfichafamiliar.activity.ConfigurationDataBase;
import z9.msp.gob.mspfichafamiliar.activity.DescargarFormularios;
import z9.msp.gob.mspfichafamiliar.activity.NuevoFormularioActivity;
import z9.msp.gob.mspfichafamiliar.utilsApp.RestClient;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.entity.Formulario;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;

import static android.R.attr.value;

public class MasterPageActivity extends AppCompatActivity{
    ImageButton btn_actualizar_datos;
    ImageButton btn_buscar_formulario;
    ImageButton btn_config_formulario;
    ImageButton btn_descargarFormulario;
    ImageButton btn_nuevoFormulario;
    ImageButton btn_subir_formualario;
    ProgressDialog progress ;
    DatabaseHandler db;
    protected  HttpURLConnection con;
    private List<String> msj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_page);
        db = new DatabaseHandler(this);
        btn_actualizar_datos = (ImageButton)findViewById(R.id.btn_actualizar_datos);
        btn_buscar_formulario = (ImageButton)findViewById(R.id.btn_buscar_formulario);
        btn_config_formulario = (ImageButton)findViewById(R.id.btn_config_formulario);
        btn_descargarFormulario = (ImageButton)findViewById(R.id.btn_descargarFormulario);
        btn_nuevoFormulario = (ImageButton)findViewById(R.id.btn_nuevoFormulario);
        btn_subir_formualario = (ImageButton)findViewById(R.id.btn_subir_formualario);
        progress = new ProgressDialog(MasterPageActivity.this);

    }



    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btn_actualizar_datos:

                    progress.setMessage(S.establecerConeccion);
                progress.show();
                final String url=db.getWs(WS.CATALOGO);
                try {
                    URL urlCat=new URL(url);
                    System.out.println("Iniciando");

                    progress.setCancelable(false);
                   new GetCommentsTask(progress,this,urlCat).execute();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_subir_formualario:
                progress.setMessage(S.establecerConeccion);
                progress.show();
                final String urlUp=db.getWs(WS.CARGA_FORMULARIOS);
                   progress.setCancelable(false);
                    new GetFormulariosTask(progress,this,urlUp).execute();


                break;
            case R.id.btn_config_formulario:
                startActivityLocal(ConfigurationDataBase.class);
                break;
            case R.id.btn_nuevoFormulario:
                startActivityLocal(NuevoFormularioActivity.class);
                break;
            case R.id.btn_buscar_formulario:
                startActivityLocal(MainActivity.class);
                break;
            case R.id.btn_descargarFormulario:
                startActivityLocal(DescargarFormularios.class);
                break;


        }
    }

    private void startActivityLocal(Class activity){
        Intent myIntent = new Intent(MasterPageActivity.this,activity);
        myIntent.putExtra("key", value); //Optional parameters
        MasterPageActivity.this.startActivity(myIntent);
    }
    public class GetFormulariosTask extends AsyncTask<String, Void, List<String>> {
        ProgressDialog progress;
        Context context;
        String url;
        public GetFormulariosTask(ProgressDialog progress, Context act, String url) {
            this.progress = progress;
            this.context= act;
            this.url=url;
        }
        public void onPreExecute() {
            progress.show();

        }
        @Override
        protected List<String> doInBackground(String... urls) {

            List<String> comments = new ArrayList<String>();
            Cursor cursor=db.getAllGeneric(TABLES.FORMULARIO.getTablaName());
            final Gson gson = new Gson();
            final JsonObject jo = new JsonObject();

            RestClient client = new RestClient(url);
            if(cursor.moveToNext()){
                do{
                    String fomularoId=cursor.getString(cursor.getColumnIndex("_id"));
                    Formulario formulario=db.exportData(fomularoId);
                    JsonElement je = gson.toJsonTree(formulario);
                    jo.add("formulario", je);
                    String representacionJSON = jo.toString();
                    String response="Error";
                    client.AddParam("formulario", representacionJSON);
                    try {
                        client.Execute(RestClient.RequestMethod.GET);

                        response = client.getResponse();
                        if(response.equals("OK")){
                            db.deleteFormularioById(fomularoId);
                        }
                    } catch (Exception e) {
                        comments.add(S.errorServer+ ":"+e.getMessage());
                        e.printStackTrace();
                    }
                }while (cursor.moveToNext());

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
    /*
La clase GetCommentsTask representa una tarea asincrona que realizará
las operaciones de red necesarias en segundo plano para obtener toda la
lista de comentarios alojada en el servidor.
 */
    public class GetCommentsTask extends AsyncTask<URL, Void, List<String>> {
        ProgressDialog progress;
        Context context;
        URL url;
        public GetCommentsTask(ProgressDialog progress, Context act, URL url) {
            this.progress = progress;
            this.context= act;
            this.url=url;
        }
        public void onPreExecute() {
            progress.show();

        }
            @Override
        protected List<String> doInBackground(URL... urls) {

            List<String> comments = new ArrayList<String>();
                try {

                    // Establecer la conexión
                    con = (HttpURLConnection)url.openConnection();
                    con.setConnectTimeout(5000);
                    // Obtener el estado del recursocon.responseCode
                    int statusCode = con.getResponseCode();

                    if(statusCode!=200) {
                        comments.add(S.responseServer+statusCode);

                    }
                    else{

                    /*
                    Parsear el flujo con formato JSON a una lista de Strings
                    que permitan crean un adaptador
                     */
                        String output;
                        StringBuilder out=new StringBuilder();
                        BufferedReader br = new BufferedReader(new InputStreamReader(
                                (con.getInputStream())));
                        System.out.println("Output from Server .... \n");

                        while ((output = br.readLine()) != null) {
                            out.append(output);
                        }
                        JSONObject response = new JSONObject(out.toString());
                        Iterator<?> keys = response.keys();
                        db.deleteData();
                        while( keys.hasNext() ) {
                            String tableName = (String)keys.next();
                            try {
                                JSONArray listObject=response.getJSONArray(tableName);
                                for(int i=0;i<listObject.length();i++){
                                    JSONObject object =listObject.optJSONObject(i);
                                    //db.insertMassive(tableName,object);
                                    TABLES table=TABLES.findByTableName(tableName);
                                    if(table!=null) {
                                        Gson gson = new Gson();
                                        Object entity = gson.fromJson(listObject.getString(i), table.getaClass());
                                        db.insert(table, entity);
                                    }


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }




                    }

                } catch (Exception e) {
                    comments.add(S.errorServer+" : "+e.getMessage());


                }finally {
                    con.disconnect();
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
