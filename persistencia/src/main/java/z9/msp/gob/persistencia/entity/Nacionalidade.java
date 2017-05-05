package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the nacionalidades database table.
 * 
 */
public class Nacionalidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_nacs")
	private Integer idNacs;

	@SerializedName("cod_nacs")
	private Integer codNacs;

	@SerializedName("descripcion")
	private String descripcion;

	public Nacionalidade() {
	}

	public Integer getIdNacs() {
		return this.idNacs;
	}

	public void setIdNacs(Integer idNacs) {
		this.idNacs = idNacs;
	}

	public Integer getCodNacs() {
		return this.codNacs;
	}

	public void setCodNacs(Integer codNacs) {
		this.codNacs = codNacs;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}