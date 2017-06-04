package z9.msp.gob.persistencia.enums;

/**
 * Created by henry on 4/30/2017.
 */

public enum WS {
    CATALOGO("/api/integracion/catalogos/"),
    CARGA_FORMULARIOS("/api/integracion/upload/"),
    DESCARGA_FORMULARIOS("/api/integracion/download/");
    String urlPath;

    WS(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
