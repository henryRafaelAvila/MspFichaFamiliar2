package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the actividad_trab database table.
 * 
 */

public class ActividadTrab implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_act_trab")
	private Integer idActTrab;

	@SerializedName("cod_act_trab")
	private Integer codActTrab;
	
	@SerializedName("descripcion")
	private String descripcion;

	public ActividadTrab() {
	}

	public Integer getIdActTrab() {
		return this.idActTrab;
	}

	public void setIdActTrab(Integer idActTrab) {
		this.idActTrab = idActTrab;
	}

	public Integer getCodActTrab() {
		return this.codActTrab;
	}

	public void setCodActTrab(Integer codActTrab) {
		this.codActTrab = codActTrab;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}