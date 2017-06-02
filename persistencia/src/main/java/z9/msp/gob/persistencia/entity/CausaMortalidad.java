package z9.msp.gob.persistencia.entity;

import java.io.Serializable;


/**
 * The persistent class for the causa_mortalidad database table.
 * 
 */
public class CausaMortalidad implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer idCauMor;

	private Integer codCauMor;

	private String descripcion;

	public CausaMortalidad() {
	}

	public Integer getIdCauMor() {
		return this.idCauMor;
	}

	public void setIdCauMor(Integer idCauMor) {
		this.idCauMor = idCauMor;
	}

	public Integer getCodCauMor() {
		return this.codCauMor;
	}

	public void setCodCauMor(Integer codCauMor) {
		this.codCauMor = codCauMor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}