package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the etnia database table.
 * 
 */
public class Etnia implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_etn")
	private Integer idEtn;

	@SerializedName("cod_etn")
	private Integer codEtn;

	@SerializedName("descripcion")
	private String descripcion;

	public Etnia() {
	}

	public Integer getIdEtn() {
		return this.idEtn;
	}

	public void setIdEtn(Integer idEtn) {
		this.idEtn = idEtn;
	}

	public Integer getCodEtn() {
		return this.codEtn;
	}

	public void setCodEtn(Integer codEtn) {
		this.codEtn = codEtn;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}