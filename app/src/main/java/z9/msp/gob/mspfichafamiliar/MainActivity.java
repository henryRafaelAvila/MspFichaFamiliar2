package z9.msp.gob.mspfichafamiliar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import z9.msp.gob.mspfichafamiliar.activity.NuevoFormularioActivity;
import z9.msp.gob.mspfichafamiliar.fragment.ListFormFragment;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.TABLES;
import z9.msp.gob.persistencia.integration.ClientRest;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DatabaseHandler db;
    /*
    Cliente para la conexión al servidor
     */
    HttpURLConnection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView;
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = new DatabaseHandler(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ACCION BOTON MAS,  NUEVO FORMLARIO
                Intent launchactivity = new  Intent(MainActivity.this, NuevoFormularioActivity.class);
                startActivity(launchactivity);
            }
        });
        ListFormFragment leadsFragment = (ListFormFragment)
                getSupportFragmentManager().findFragmentById(R.id.content_list_form);

        if (leadsFragment == null) {
            leadsFragment = ListFormFragment.newInstance("parm1","parm2");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_list_form, leadsFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //pruebas
        //<code></code>find
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
     /*
            Comprobar la disponibilidad de la Red
             */
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
//                final ClientRest clientRest=new ClientRest();
//                String json=clientRest.popupaleDataBAse("myURL");
//                System.out.println(json);
                try {
                    new GetCommentsTask().
                            execute(
                                    new URL("http://192.168.0.101:8090/msp.ficha/api/Catalog/datos"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
            else{
                Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        }
        if (id == R.id.nav_manage) {
            //replaceFragment(new ConfiguracionActivity());
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*
    La clase GetCommentsTask representa una tarea asincrona que realizará
    las operaciones de red necesarias en segundo plano para obtener toda la
    lista de comentarios alojada en el servidor.
     */
    public class GetCommentsTask extends AsyncTask<URL, Void, List<String>> {

        @Override
        protected List<String> doInBackground(URL... urls) {

            List<String> comments = null;

            try {

                // Establecer la conexión
                con = (HttpURLConnection)urls[0].openConnection();

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    comments = new ArrayList<>();
                    comments.add("El recurso no está disponible");
                    return comments;
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
                    comments = new ArrayList<>();
                    comments.add(out.toString());
                    System.out.println(out.toString());
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
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return comments;

        }

        @Override
        protected void onPostExecute(List<String> s) {

            for (String sItem:s){
                System.out.println("Output from Server .... \n" +sItem);
            }
        }
    }
}
