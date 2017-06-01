package z9.msp.gob.persistencia.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
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

public class Utilitarios {

    public static Date stringToDate(String fechaVisita) {

        Date date = null;
        if(fechaVisita!=null){
            int sizeDateToFormat=fechaVisita.length();
            String fomatDate;
            if(sizeDateToFormat<11){
                DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
            try {
                    date=format.parse(fechaVisita);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
               date =new Date(fechaVisita);
            }


       return  date;
        }
        return date;
    }
    public static String dateToString(Date fechaVisita) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String reportDate="";
       reportDate = format.format(fechaVisita);

        return  reportDate;
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
}
