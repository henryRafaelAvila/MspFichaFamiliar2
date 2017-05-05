package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the clasif_diagnostico database table.
 * 
 */
public class ClasifDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_claf_diag")
	private Integer idClafDiag;

	@SerializedName("cod_claf_diag")
	private String codClafDiag;

	@SerializedName("descripcion")
	private String descripcion;

	public ClasifDiagnostico() {
	}

	public Integer getIdClafDiag() {
		return this.idClafDiag;
	}

	public void setIdClafDiag(Integer idClafDiag) {
		this.idClafDiag = idClafDiag;
	}

	public String getCodClafDiag() {
		return this.codClafDiag;
	}

	public void setCodClafDiag(String codClafDiag) {
		this.codClafDiag = codClafDiag;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}