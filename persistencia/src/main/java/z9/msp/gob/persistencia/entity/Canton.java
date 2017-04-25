package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Canton implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_canton")
	private Integer idCanton;

	@SerializedName("cod_cant")
	private String codCant;

	private String descripcion;



	public Canton() {
	}

	public Integer getIdCanton() {
		return this.idCanton;
	}

	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

	public String getCodCant() {
		return this.codCant;
	}

	public void setCodCant(String codCant) {
		this.codCant = codCant;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}