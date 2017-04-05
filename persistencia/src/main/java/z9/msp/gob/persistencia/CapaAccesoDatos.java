package z9.msp.gob.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Naty on 04/04/17.
 */

public class CapaAccesoDatos {
    public  static final  String DB_PATH="/data/data/z9.msp.gob.mspfichafamiliar/databases/";
    public  static final  String DB_NAME="ficha_familiar_msp";
    public  static final int DB_Version=1;

    private Context context;
    private SQLiteDatabase database;
    private DBHandler dbhandler;

    public  CapaAccesoDatos (Context context){
        this.context=context;
        dbhandler=new DBHandler(context);
        dbhandler.creadb();
        database=SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
    }
}
