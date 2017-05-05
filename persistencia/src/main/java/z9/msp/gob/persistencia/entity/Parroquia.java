package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_parroquia")
	private Integer idParroquia;

	@SerializedName("cod_parr")
	private String codParr;

	private String descripcion;

	private String tipo;


	public Parroquia() {
	}

	public Integer getIdParroquia() {
		return this.idParroquia;
	}

	public void setIdParroquia(Integer idParroquia) {
		this.idParroquia = idParroquia;
	}

	public String getCodParr() {
		return this.codParr;
	}

	public void setCodParr(String codParr) {
		this.codParr = codParr;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}