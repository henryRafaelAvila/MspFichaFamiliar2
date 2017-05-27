package z9.msp.gob.persistencia.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by henry on 5/22/2017.
 */

public class Utilitarios {

    public static Date stringToDate(String fechaVisita) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = null;
        try {
            date=format.parse(fechaVisita);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       return  date;
    }
}
