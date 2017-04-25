package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class EliminarAguaSer implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_elim_agu_ser")
	private Integer idElimAguSer;

	@SerializedName("cod_agua_ser")
	private Integer codAguaSer;

	@SerializedName("descripcion")
	private String descripcion;

	public EliminarAguaSer() {
	}

	public Integer getIdElimAguSer() {
		return this.idElimAguSer;
	}

	public void setIdElimAguSer(Integer idElimAguSer) {
		this.idElimAguSer = idElimAguSer;
	}

	public Integer getCodAguaSer() {
		return this.codAguaSer;
	}

	public void setCodAguaSer(Integer codAguaSer) {
		this.codAguaSer = codAguaSer;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}