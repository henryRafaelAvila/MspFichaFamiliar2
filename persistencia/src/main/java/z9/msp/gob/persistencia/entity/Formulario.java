package z9.msp.gob.persistencia.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import z9.msp.gob.persistencia.entity.Mortalidad;
import z9.msp.gob.persistencia.entity.Personas;
import z9.msp.gob.persistencia.utils.Utilitarios;


/**
 * The persistent class for the formulario database table.
 *
 */
public class Formulario implements Serializable {
    private static final long serialVersionUID = 1L;

    String nombre;
    String descripcion;
    String zona;

    private Integer idFormulario;

    private Integer abandono;

    private Integer aepi;

    private Integer aisla;

    private Integer alcoh;

    private Integer animViv;

    private String calle1;

    private String calle2;

    private String celular;

    private Integer cocInhog;

    private Integer codigo;

    private String contaminaAguDesc;

    private String contaminaAirDesc;

    private String contaminaSuelDesc;

    private String coordenadas;

    private Integer destFami;

    private Integer droga;

    private String edificio;

    private String entrCell;

    private String entrTelf;

    private Date fechaVisita;

    private Integer idCombCoc;

    private Integer idCondOcup;

    private Integer idEliAgu;

    private Integer idEliBas;

    private Integer idEstPis;

    private Integer idEstTech;

    private Integer idMatPar;

    private Integer idMatPis;

    private Integer idMatTec;

    private Integer idProAgudd;

    private Integer idRecAgu;

    private Integer idTipTrans;

    private Integer idTipViv;

    private Integer idTraAgu;

    private Integer idUbiLet;

    private Integer idUnidOper;

    private Integer idViaAcc;

    private String institucion;

    private Integer intradomi;

    private Integer irUniSal;

    private String localidad;

    private Integer manzana;

    private Integer mosquit;

    private Integer ninNoescola;

    private String numCuar;

    private String numDorm;

    private String persRefCell;

    private String persRefTelf;

    private Integer plaguicida;

    private Integer probGraFam;

    private Integer psicoSoc;

    private String responsable;

    private Integer sedazo;

    private Integer sinEscol;

    private String telefono;

    private Integer tiemVivMeses;

    private Integer tiempVivAnios;

    private Integer tiempoTransporte;

    private Integer vecTrans;

    private Integer violFami;

    private List<Mortalidad> mortalidads;

    //bi-directional many-to-one association to Persona
    private List<Personas> personas;

    public Formulario() {
    }
    public Formulario(String nombre, String descripcion, String zona, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
        this.idFormulario=id;
    }

    public Integer getIdFormulario() {
        return this.idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Integer getAbandono() {
        return this.abandono;
    }

    public void setAbandono(Integer abandono) {
        this.abandono = abandono;
    }

    public Integer getAepi() {
        return this.aepi;
    }

    public void setAepi(Integer aepi) {
        this.aepi = aepi;
    }

    public Integer getAisla() {
        return this.aisla;
    }

    public void setAisla(Integer aisla) {
        this.aisla = aisla;
    }

    public Integer getAlcoh() {
        return this.alcoh;
    }

    public void setAlcoh(Integer alcoh) {
        this.alcoh = alcoh;
    }

    public Integer getAnimViv() {
        return this.animViv;
    }

    public void setAnimViv(Integer animViv) {
        this.animViv = animViv;
    }

    public String getCalle1() {
        return this.calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return this.calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getCocInhog() {
        return this.cocInhog;
    }

    public void setCocInhog(Integer cocInhog) {
        this.cocInhog = cocInhog;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getContaminaAguDesc() {
        return this.contaminaAguDesc;
    }

    public void setContaminaAguDesc(String contaminaAguDesc) {
        this.contaminaAguDesc = contaminaAguDesc;
    }

    public String getContaminaAirDesc() {
        return this.contaminaAirDesc;
    }

    public void setContaminaAirDesc(String contaminaAirDesc) {
        this.contaminaAirDesc = contaminaAirDesc;
    }

    public String getContaminaSuelDesc() {
        return this.contaminaSuelDesc;
    }

    public void setContaminaSuelDesc(String contaminaSuelDesc) {
        this.contaminaSuelDesc = contaminaSuelDesc;
    }

    public String getCoordenadas() {
        return this.coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getDestFami() {
        return this.destFami;
    }

    public void setDestFami(Integer destFami) {
        this.destFami = destFami;
    }

    public Integer getDroga() {
        return this.droga;
    }

    public void setDroga(Integer droga) {
        this.droga = droga;
    }

    public String getEdificio() {
        return this.edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getEntrCell() {
        return this.entrCell;
    }

    public void setEntrCell(String entrCell) {
        this.entrCell = entrCell;
    }

    public String getEntrTelf() {
        return this.entrTelf;
    }

    public void setEntrTelf(String entrTelf) {
        this.entrTelf = entrTelf;
    }

    public Date getFechaVisita() {
        return this.fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        Date date= Utilitarios.stringToDate(fechaVisita);
        this.fechaVisita = date;
    }

    public Integer getIdCombCoc() {
        return this.idCombCoc;
    }

    public void setIdCombCoc(Integer idCombCoc) {
        this.idCombCoc = idCombCoc;
    }

    public Integer getIdCondOcup() {
        return this.idCondOcup;
    }

    public void setIdCondOcup(Integer idCondOcup) {
        this.idCondOcup = idCondOcup;
    }

    public Integer getIdEliAgu() {
        return this.idEliAgu;
    }

    public void setIdEliAgu(Integer idEliAgu) {
        this.idEliAgu = idEliAgu;
    }

    public Integer getIdEliBas() {
        return this.idEliBas;
    }

    public void setIdEliBas(Integer idEliBas) {
        this.idEliBas = idEliBas;
    }

    public Integer getIdEstPis() {
        return this.idEstPis;
    }

    public void setIdEstPis(Integer idEstPis) {
        this.idEstPis = idEstPis;
    }

    public Integer getIdEstTech() {
        return this.idEstTech;
    }

    public void setIdEstTech(Integer idEstTech) {
        this.idEstTech = idEstTech;
    }

    public Integer getIdMatPar() {
        return this.idMatPar;
    }

    public void setIdMatPar(Integer idMatPar) {
        this.idMatPar = idMatPar;
    }

    public Integer getIdMatPis() {
        return this.idMatPis;
    }

    public void setIdMatPis(Integer idMatPis) {
        this.idMatPis = idMatPis;
    }

    public Integer getIdMatTec() {
        return this.idMatTec;
    }

    public void setIdMatTec(Integer idMatTec) {
        this.idMatTec = idMatTec;
    }

    public Integer getIdProAgudd() {
        return this.idProAgudd;
    }

    public void setIdProAgudd(Integer idProAgudd) {
        this.idProAgudd = idProAgudd;
    }

    public Integer getIdRecAgu() {
        return this.idRecAgu;
    }

    public void setIdRecAgu(Integer idRecAgu) {
        this.idRecAgu = idRecAgu;
    }

    public Integer getIdTipTrans() {
        return this.idTipTrans;
    }

    public void setIdTipTrans(Integer idTipTrans) {
        this.idTipTrans = idTipTrans;
    }

    public Integer getIdTipViv() {
        return this.idTipViv;
    }

    public void setIdTipViv(Integer idTipViv) {
        this.idTipViv = idTipViv;
    }

    public Integer getIdTraAgu() {
        return this.idTraAgu;
    }

    public void setIdTraAgu(Integer idTraAgu) {
        this.idTraAgu = idTraAgu;
    }

    public Integer getIdUbiLet() {
        return this.idUbiLet;
    }

    public void setIdUbiLet(Integer idUbiLet) {
        this.idUbiLet = idUbiLet;
    }

    public Integer getIdUnidOper() {
        return this.idUnidOper;
    }

    public void setIdUnidOper(Integer idUnidOper) {
        this.idUnidOper = idUnidOper;
    }

    public Integer getIdViaAcc() {
        return this.idViaAcc;
    }

    public void setIdViaAcc(Integer idViaAcc) {
        this.idViaAcc = idViaAcc;
    }

    public String getInstitucion() {
        return this.institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Integer getIntradomi() {
        return this.intradomi;
    }

    public void setIntradomi(Integer intradomi) {
        this.intradomi = intradomi;
    }

    public Integer getIrUniSal() {
        return this.irUniSal;
    }

    public void setIrUniSal(Integer irUniSal) {
        this.irUniSal = irUniSal;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getManzana() {
        return this.manzana;
    }

    public void setManzana(Integer manzana) {
        this.manzana = manzana;
    }

    public Integer getMosquit() {
        return this.mosquit;
    }

    public void setMosquit(Integer mosquit) {
        this.mosquit = mosquit;
    }

    public Integer getNinNoescola() {
        return this.ninNoescola;
    }

    public void setNinNoescola(Integer ninNoescola) {
        this.ninNoescola = ninNoescola;
    }

    public String getNumCuar() {
        return this.numCuar;
    }

    public void setNumCuar(String numCuar) {
        this.numCuar = numCuar;
    }

    public String getNumDorm() {
        return this.numDorm;
    }

    public void setNumDorm(String numDorm) {
        this.numDorm = numDorm;
    }

    public String getPersRefCell() {
        return this.persRefCell;
    }

    public void setPersRefCell(String persRefCell) {
        this.persRefCell = persRefCell;
    }

    public String getPersRefTelf() {
        return this.persRefTelf;
    }

    public void setPersRefTelf(String persRefTelf) {
        this.persRefTelf = persRefTelf;
    }

    public Integer getPlaguicida() {
        return this.plaguicida;
    }

    public void setPlaguicida(Integer plaguicida) {
        this.plaguicida = plaguicida;
    }

    public Integer getProbGraFam() {
        return this.probGraFam;
    }

    public void setProbGraFam(Integer probGraFam) {
        this.probGraFam = probGraFam;
    }

    public Integer getPsicoSoc() {
        return this.psicoSoc;
    }

    public void setPsicoSoc(Integer psicoSoc) {
        this.psicoSoc = psicoSoc;
    }

    public String getResponsable() {
        return this.responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getSedazo() {
        return this.sedazo;
    }

    public void setSedazo(Integer sedazo) {
        this.sedazo = sedazo;
    }

    public Integer getSinEscol() {
        return this.sinEscol;
    }

    public void setSinEscol(Integer sinEscol) {
        this.sinEscol = sinEscol;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTiemVivMeses() {
        return this.tiemVivMeses;
    }

    public void setTiemVivMeses(Integer tiemVivMeses) {
        this.tiemVivMeses = tiemVivMeses;
    }

    public Integer getTiempVivAnios() {
        return this.tiempVivAnios;
    }

    public void setTiempVivAnios(Integer tiempVivAnios) {
        this.tiempVivAnios = tiempVivAnios;
    }

    public Integer getTiempoTransporte() {
        return this.tiempoTransporte;
    }

    public void setTiempoTransporte(Integer tiempoTransporte) {
        this.tiempoTransporte = tiempoTransporte;
    }

    public Integer getVecTrans() {
        return this.vecTrans;
    }

    public void setVecTrans(Integer vecTrans) {
        this.vecTrans = vecTrans;
    }

    public Integer getViolFami() {
        return this.violFami;
    }

    public void setViolFami(Integer violFami) {
        this.violFami = violFami;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public List<Mortalidad> getMortalidads() {
        return mortalidads;
    }

    public void setMortalidads(List<Mortalidad> mortalidads) {
        this.mortalidads = mortalidads;
    }

    public List<Personas> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Personas> personas) {
        this.personas = personas;
    }
}