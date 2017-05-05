package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class MaterialTecho implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_mat_tec")
	private Integer idMatTec;

	@SerializedName("cod_mat_tec")
	private Integer codMatTec;

	private String descripcion;

	public MaterialTecho() {
	}

	public Integer getIdMatTec() {
		return this.idMatTec;
	}

	public void setIdMatTec(Integer idMatTec) {
		this.idMatTec = idMatTec;
	}

	public Integer getCodMatTec() {
		return this.codMatTec;
	}

	public void setCodMatTec(Integer codMatTec) {
		this.codMatTec = codMatTec;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}