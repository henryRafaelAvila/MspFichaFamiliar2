package z9.msp.gob.persistencia.entity;

import java.io.Serializable;


import com.google.gson.annotations.SerializedName;


public class EstadoPiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_est_pis")
	private Integer idEstPis;

	@SerializedName("cod_est_pis")
	private Integer codEstPis;

	private String descripcion;

	public EstadoPiso() {
	}

	public Integer getIdEstPis() {
		return this.idEstPis;
	}

	public void setIdEstPis(Integer idEstPis) {
		this.idEstPis = idEstPis;
	}

	public Integer getCodEstPis() {
		return this.codEstPis;
	}

	public void setCodEstPis(Integer codEstPis) {
		this.codEstPis = codEstPis;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}