package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class EstadoPared implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_est_par")
	private Integer idEstPar;

	@SerializedName("cod_est_par")
	private Integer codEstPar;
	
	@SerializedName("descripcion")
	private String descripcion;

	public EstadoPared() {
	}

	public Integer getIdEstPar() {
		return this.idEstPar;
	}

	public void setIdEstPar(Integer idEstPar) {
		this.idEstPar = idEstPar;
	}

	public Integer getCodEstPar() {
		return this.codEstPar;
	}

	public void setCodEstPar(Integer codEstPar) {
		this.codEstPar = codEstPar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}