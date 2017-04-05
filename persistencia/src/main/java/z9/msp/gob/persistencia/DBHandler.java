package z9.msp.gob.persistencia;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 3/26/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ficha_familiar_msp";
    private Context context;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("********CREATE TABLES DATABASE*************");
        System.out.println("*******************************************");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private boolean compruebabd(){

        boolean checkdb=false;

        String path= CapaAccesoDatos.DB_PATH + CapaAccesoDatos.DB_NAME;
        File ficherodb = new File(path);
        checkdb=ficherodb.exists();
        return  checkdb;
    }

    public void creadb(){
        boolean existe=compruebabd();
        if (existe){
        }
        else{
            this.getReadableDatabase();
            copiabd();
        }
    }

    public  void copiabd(){
        try{
            InputStream in=context.getAssets().open(CapaAccesoDatos.DB_NAME);

            String ruta=CapaAccesoDatos.DB_PATH + CapaAccesoDatos.DB_NAME;

            OutputStream salida= new FileOutputStream(ruta);

            byte [] buffer = new byte[1024];
            int tam;

            while((tam=in.read(buffer))> 0){
                salida.write(buffer,0,tam);

            }
            salida.flush();
            salida.close();
            in.close();
        }catch (Exception e){

        }
    }

}
