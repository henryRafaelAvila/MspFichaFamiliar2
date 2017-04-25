package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class EstadoTecho implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_est_tech")
	private Integer idEstTech;

	@SerializedName("cod_est_tech")
	private Integer codEstTech;

	private String descripcion;

	public EstadoTecho() {
	}

	public Integer getIdEstTech() {
		return this.idEstTech;
	}

	public void setIdEstTech(Integer idEstTech) {
		this.idEstTech = idEstTech;
	}

	public Integer getCodEstTech() {
		return this.codEstTech;
	}

	public void setCodEstTech(Integer codEstTech) {
		this.codEstTech = codEstTech;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}