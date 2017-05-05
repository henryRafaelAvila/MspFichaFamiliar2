package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CondicionOcupacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_cond_ocup")
	private Integer idCondOcup;

	@SerializedName("cod_ocup")
	private Integer codOcup;

	private String descripcion;

	public CondicionOcupacion() {
	}

	public Integer getIdCondOcup() {
		return this.idCondOcup;
	}

	public void setIdCondOcup(Integer idCondOcup) {
		this.idCondOcup = idCondOcup;
	}

	public Integer getCodOcup() {
		return this.codOcup;
	}

	public void setCodOcup(Integer codOcup) {
		this.codOcup = codOcup;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}