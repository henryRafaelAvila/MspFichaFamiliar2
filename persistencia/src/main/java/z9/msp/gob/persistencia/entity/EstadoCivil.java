package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the estado_civil database table.
 * 
 */
public class EstadoCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_est_civ")	
	private Integer idEstCiv;

	@SerializedName("cod_est_civ")
	private Integer codEstCiv;

	@SerializedName("descripcion")
	private String descripcion;

	public EstadoCivil() {
	}

	public Integer getIdEstCiv() {
		return this.idEstCiv;
	}

	public void setIdEstCiv(Integer idEstCiv) {
		this.idEstCiv = idEstCiv;
	}

	public Integer getCodEstCiv() {
		return this.codEstCiv;
	}

	public void setCodEstCiv(Integer codEstCiv) {
		this.codEstCiv = codEstCiv;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}