package z9.msp.gob.mspfichafamiliar.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.DatabaseHandler;
import z9.msp.gob.persistencia.enums.TABLES;

public class ConfigurationDataBase extends AppCompatActivity {
    EditText et_direccionIp;
    EditText et_puerto;
    EditText et_catalogos;
    EditText et_uploadForm;
    EditText et_downloadForm;
    EditText et_usuario;
    EditText et_clave;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_data_base);
        initComponets();
        db = new DatabaseHandler(this);
        //CREATE TABLE config_server (_id INTEGER PRIMARY KEY  NOT NULL , ip VARCHAR NOT NULL , puerto INTEGER NOT NULL,
        // servicio_cat VARCHAR,servicio_up VARCHAR,servicio_down VARCHAR)"),
        Cursor cursor=db.getAllGeneric("config_server");
        if (cursor.moveToFirst()) {
            et_direccionIp.setText(cursor.getString(1));
            et_puerto.setText(cursor.getInt(2)+"");
            et_catalogos.setText(cursor.getString(3));
            et_uploadForm.setText(cursor.getString(4));
            et_downloadForm.setText(cursor.getString(5));
            et_usuario.setText(cursor.getString(6));
            et_clave.setText(cursor.getString(7));
        }
    }
    private boolean hasError(){
        if(validateEditText(et_direccionIp,"Dirección IP")) return true;
        if(validateEditText(et_puerto,"Puerto")) return true;
        if(validateEditText(et_catalogos,"Ws Catálogos")) return true;
        if(validateEditText(et_uploadForm,"Ws subida")) return true;
        if(validateEditText(et_uploadForm,"Ws descargar")) return true;
        if(validateEditText(et_usuario,"Usuario")) return true;
        if(validateEditText(et_clave,"Clave")) return true;
        return  false;
    }
    private boolean validateEditText(EditText editText,String nameItem){
        boolean  isEmpty=false;
        if( editText.getText().toString().trim().equals("")){
            showMessage(nameItem+" es obligatorio!.");
            isEmpty=true;
        }
        return isEmpty;
    }
    public void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    public void saveData(View view){
        if(!hasError()){
        TABLES table=TABLES.CONFIG_SERVER;
        db.deleteData(table);
        switch (view.getId()){
            case R.id.btn_save_config_server:
                ContentValues values = new ContentValues();
                values.put("ip", et_direccionIp.getText().toString());
                values.put("puerto", Integer.parseInt(et_puerto.getText().toString()));
                values.put("servicio_cat",et_catalogos.getText().toString());
                values.put("servicio_up",et_uploadForm.getText().toString());
                values.put("servicio_down",et_downloadForm.getText().toString());
                values.put("usuario",et_usuario.getText().toString());
                values.put("clave",et_clave.getText().toString());
                db.executeCreateQuery(values,table);
                break;
        }
            showMessage("Datos guardados correctamente. !");
        }
    }
    private void initComponets(){
         et_direccionIp=(EditText)findViewById(R.id.et_direccionIp);
         et_puerto=(EditText)findViewById(R.id.et_puerto);
         et_catalogos=(EditText)findViewById(R.id.et_catalogos);
         et_uploadForm=(EditText)findViewById(R.id.et_uploadForm);
         et_downloadForm=(EditText)findViewById(R.id.et_downloadForm);
        et_usuario=(EditText)findViewById(R.id.et_usuario);
        et_clave=(EditText)findViewById(R.id.et_clave);
    }
}
