package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class MaterialPared implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_mat_par")
	private Integer idMatPar;

	@SerializedName("cod_mat_par")
	private Integer codMatPar;

	private String descripcion;

	public MaterialPared() {
	}

	public Integer getIdMatPar() {
		return this.idMatPar;
	}

	public void setIdMatPar(Integer idMatPar) {
		this.idMatPar = idMatPar;
	}

	public Integer getCodMatPar() {
		return this.codMatPar;
	}

	public void setCodMatPar(Integer codMatPar) {
		this.codMatPar = codMatPar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}