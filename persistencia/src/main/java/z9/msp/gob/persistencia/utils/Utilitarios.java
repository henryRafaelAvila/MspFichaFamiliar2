package z9.msp.gob.persistencia.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AlertDialog;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by henry on 5/22/2017.
 */

public final class Utilitarios {
    public  static final String formateDate_ddMMyyyy="dd/MM/yyyy";
    public static boolean decodeBoolean(String op){
        boolean status=false;
        if(op!=null&&!op.equals("")){
            if(op.equals("1")){
                status=true;
            }
        }
        return status;
    }
    public static boolean decodeBoolean(Integer op){
        boolean status=false;
        try{
            String valor=String.valueOf(op);
            status=decodeBoolean(valor);
        }catch (Exception e){
            status=false;
        }finally {
            return status;
        }
    }
    public static Date stringToDateLongFormat(String fechaVisita) {

        Date date = null;
        if(fechaVisita!=null){
            DateFormat format = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
            try {
                date=format.parse(fechaVisita);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static Date stringToDate(String fechaVisita) {

        Date date = null;
        if(fechaVisita!=null){

            if(fechaVisita.contains("/")){
                DateFormat format = new SimpleDateFormat(formateDate_ddMMyyyy);
            try {
                    date=format.parse(fechaVisita);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
               date =stringToDateLongFormat(fechaVisita);
            }


       return  date;
        }
        return date;
    }
    public static String dateToString(Date fechaVisita) {
        String reportDate = "";
        if(fechaVisita!=null) {
            DateFormat format = new SimpleDateFormat(formateDate_ddMMyyyy);

            reportDate = format.format(fechaVisita);
        }
        return  reportDate;
    }
    public static void showMessage(String msj, Context context) {
        AlertDialog.Builder goLogin = new AlertDialog.Builder(context);
        goLogin.setMessage(msj);
        goLogin.setCancelable(false);
        goLogin.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });
        AlertDialog alertLogin = goLogin.create();
        alertLogin.show();
    }
    public static String decodeNull(String value) {
        String resp=null;
        if(value!=null&&value.equals("0")){
            value=resp;
        }
        return  value;
    }
    public static int getPosition(Spinner spinner,Integer value) {
        return  getPosition(spinner,value+"");
    }
    public static int getPosition(Spinner spinner,String value) {
        int pos=0;

        if(spinner!=null){
            int tot=spinner.getCount();
            SQLiteCursor cursor=null;
            for (int i=0;i<tot;i++){
                cursor=(SQLiteCursor)spinner.getItemAtPosition(i);
                String content=cursor.getString(cursor.getColumnIndex("_id"));
                if(content!=null&&content.equals(value)){
                    pos=i;
                    break;
                }

            }
        }
        return pos;
    }
    public static ContentValues decodeContentValues(ContentValues contentValues){
        Set<Map.Entry<String, Object>> s=contentValues.valueSet();
        Iterator itr = s.iterator();
        while(itr.hasNext())
        {
            Map.Entry me = (Map.Entry)itr.next();
            String key = me.getKey().toString();
            Object value =  me.getValue();
            if(key.startsWith("id_")){
                if(value instanceof Integer){
                    if(((Integer)(value))<1){
                        contentValues.remove(key);
                    }
                }
            }
        }
        return contentValues;
    }

    /**
     * Validacion de cedula, retorn null cuando la cedula es correcta
     * @param cedula
     * @return
     */
    public static String validarCedula(String cedula) {
        String msj = null;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        msj = null;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        msj = null;
                    } else {
                        msj = SM.errorCedula;
                    }
                } else {
                    msj = SM.errorCedula;
                }
            } else {
                msj = SM.errorCedula;
            }
        } catch (NumberFormatException nfe) {
            msj = SM.errorCedula;
        } catch (Exception err) {
            msj = SM.errorApp+err.getMessage();
        }

        return msj;
    }
}
