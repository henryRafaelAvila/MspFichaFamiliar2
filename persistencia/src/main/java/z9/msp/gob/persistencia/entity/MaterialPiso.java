package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class MaterialPiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_mat_pis")
	private Integer idMatPis;

	@SerializedName("cod_mat_pis")
	private String codMatPis;

	private String descripcion;

	public MaterialPiso() {
	}

	public Integer getIdMatPis() {
		return this.idMatPis;
	}

	public void setIdMatPis(Integer idMatPis) {
		this.idMatPis = idMatPis;
	}

	public String getCodMatPis() {
		return this.codMatPis;
	}

	public void setCodMatPis(String codMatPis) {
		this.codMatPis = codMatPis;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}