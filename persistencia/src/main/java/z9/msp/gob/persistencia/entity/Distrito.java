package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_distrito")
	private Integer idDistrito;

	@SerializedName("cod_distrito")
	private String codDistrito;

	private String descripcion;


	public Distrito() {
	}

	public Integer getIdDistrito() {
		return this.idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getCodDistrito() {
		return this.codDistrito;
	}

	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}