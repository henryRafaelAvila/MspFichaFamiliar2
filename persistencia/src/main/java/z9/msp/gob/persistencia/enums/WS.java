package z9.msp.gob.persistencia.enums;

/**
 * Created by henry on 4/30/2017.
 */

public enum WS {
    CATALOGO("servicio_cat"),
    CARGA_FORMULARIOS("servicio_up"),
    DESCARGA_FORMULARIOS("servicio_down");
    String columnName;

    WS(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
