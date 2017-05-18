package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;



public class UnidadOperativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_unid_oper")
	private Integer idUnidOper;

	@SerializedName("cod_uni_oper")
	private String codUniOper;

	private String direccion;

	@SerializedName("nombre_comun")
	private String nombreComun;

	@SerializedName("nombre_oficial")
	private String nombreOficial;

	private String telf;

	@SerializedName("id_parroquia")
	private Integer id_parroquia;

	public UnidadOperativa() {
	}

	public Integer getIdUnidOper() {
		return this.idUnidOper;
	}

	public void setIdUnidOper(Integer idUnidOper) {
		this.idUnidOper = idUnidOper;
	}

	public String getCodUniOper() {
		return this.codUniOper;
	}

	public void setCodUniOper(String codUniOper) {
		this.codUniOper = codUniOper;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreComun() {
		return this.nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreOficial() {
		return this.nombreOficial;
	}

	public void setNombreOficial(String nombreOficial) {
		this.nombreOficial = nombreOficial;
	}

	public String getTelf() {
		return this.telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public Integer getId_parroquia() {
		return id_parroquia;
	}

	public void setId_parroquia(Integer id_parroquia) {
		this.id_parroquia = id_parroquia;
	}
}