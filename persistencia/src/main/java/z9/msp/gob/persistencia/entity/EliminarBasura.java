package z9.msp.gob.persistencia.entity;

import java.io.Serializable;


import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the eliminar_basura database table.
 * 
 */
public class EliminarBasura implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_eli_bas")
	private Integer idEliBas;

	@SerializedName("cod_eli_bas")
	private Integer codEliBas;

	@SerializedName("descripcion")
	private String descripcion;

	public EliminarBasura() {
	}

	public Integer getIdEliBas() {
		return this.idEliBas;
	}

	public void setIdEliBas(Integer idEliBas) {
		this.idEliBas = idEliBas;
	}

	public Integer getCodEliBas() {
		return this.codEliBas;
	}

	public void setCodEliBas(Integer codEliBas) {
		this.codEliBas = codEliBas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}