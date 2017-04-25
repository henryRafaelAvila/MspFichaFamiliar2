package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



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

}