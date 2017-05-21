package z9.msp.gob.persistencia.entity;

import z9.msp.gob.persistencia.enums.CLS_DISCR;
import z9.msp.gob.persistencia.enums.TABLES;

/**
 * Created by henry on 5/20/2017.
 */

public class Parameters {
    TABLES TABLE;
    CLS_DISCR COLS;
//    int entero;
    String cadena;
  /*  public void setValue(int value){
        this.entero=value;
    }*/

    public Parameters(TABLES TABLE, CLS_DISCR COLS, String cadena) {
        this.TABLE = TABLE;
        this.COLS = COLS;
        this.cadena = cadena;
    }

    public void setValue(String value){
        this.cadena=value;
    }
   /* public int getValueInt(){
        return  this.entero;
    }*/
    public String getValueString(){
        return  this.cadena;
    }

    public CLS_DISCR getCOLS() {
        return COLS;
    }

    public void setCOLS(CLS_DISCR COLS) {
        this.COLS = COLS;
    }

    public TABLES getTABLE() {
        return TABLE;
    }

    public void setTABLE(TABLES TABLE) {
        this.TABLE = TABLE;
    }
}
