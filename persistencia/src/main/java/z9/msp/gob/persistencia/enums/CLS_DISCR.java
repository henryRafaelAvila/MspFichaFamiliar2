package z9.msp.gob.persistencia.enums;

/**
 * Created by henry on 5/20/2017.
 */

public enum CLS_DISCR {
    FORMULARIO_ID("id_formulario"),
    ID("_id");
    String colsName;

    CLS_DISCR(String colsName) {
        this.colsName = colsName;
    }

    public String getColsName() {
        return colsName;
    }

    public void setColsName(String colsName) {
        this.colsName = colsName;
    }
}
