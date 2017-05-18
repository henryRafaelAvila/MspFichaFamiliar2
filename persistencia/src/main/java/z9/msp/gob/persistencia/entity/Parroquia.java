package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_parroquia")
	private Integer idParroquia;

	@SerializedName("cod_parr")
	private String codParr;

	private String descripcion;

	private String tipo;


	@SerializedName("id_canton")
	private Integer id_canton;


	@SerializedName("id_distrito")
	private Integer id_distrito;


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

	public Integer getId_canton() {
		return id_canton;
	}

	public void setId_canton(Integer id_canton) {
		this.id_canton = id_canton;
	}

	public Integer getId_distrito() {
		return id_distrito;
	}

	public void setId_distrito(Integer id_distrito) {
		this.id_distrito = id_distrito;
	}
}