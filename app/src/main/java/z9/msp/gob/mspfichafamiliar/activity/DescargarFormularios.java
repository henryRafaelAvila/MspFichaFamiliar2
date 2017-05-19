package z9.msp.gob.mspfichafamiliar.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.S;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.enums.WS;

public class DescargarFormularios extends AppCompatActivity {
    ProgressDialog progress ;
    DatabaseHandler db;
    protected  HttpURLConnection con;
    private List<String> msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar_formularios);
        db = new DatabaseHandler(this);
        progress = new ProgressDialog(DescargarFormularios.this);
    }
    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btn_actualizar_datos:

                progress.setMessage(S.establecerConeccion);
                progress.show();
                String url=db.getWs(WS.DESCARGA_FORMULARIOS);
                try {
                    URL urlCat=new URL(url);
                    progress.setCancelable(false);
                    List numberFormualrio=new ArrayList();
                    String fechaIni=null,fechaFin=null;
                    new GetFormularios(progress,this,urlCat,numberFormualrio,fechaIni,fechaFin).execute();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;


        }
    }
    /*
La clase GetCommentsTask representa una tarea asincrona que realizará
las operaciones de red necesarias en segundo plano para obtener toda la
lista de comentarios alojada en el servidor.
*/
    public class GetFormularios extends AsyncTask<URL, Void, List<String>> {
        ProgressDialog progress;
        Context context;
        URL url;
        public GetFormularios(ProgressDialog progress, Context act, URL url, List numberFormularios,String fechaIni,String fechaFin) {
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
