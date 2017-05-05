package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the nivel_instruccion database table.
 * 
 */
public class NivelInstruccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_niv_inst")
	private Integer idNivInst;

	@SerializedName("cod_niv_inst")
	private Integer codNivInst;

	@SerializedName("descripcion")
	private String descripcion;

	public NivelInstruccion() {
	}

	public Integer getIdNivInst() {
		return this.idNivInst;
	}

	public void setIdNivInst(Integer idNivInst) {
		this.idNivInst = idNivInst;
	}

	public Integer getCodNivInst() {
		return this.codNivInst;
	}

	public void setCodNivInst(Integer codNivInst) {
		this.codNivInst = codNivInst;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}