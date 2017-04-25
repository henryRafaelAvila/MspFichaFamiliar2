package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;



/**
 * The persistent class for the pueblos database table.
 * 
 */

public class Pueblo implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_pue")
	private Integer idPue;
	@SerializedName("cod_pue")
	private Integer codPue;
	@SerializedName("descripcion")
	private String descripcion;

	public Pueblo() {
	}

	public Integer getIdPue() {
		return this.idPue;
	}

	public void setIdPue(Integer idPue) {
		this.idPue = idPue;
	}

	public Integer getCodPue() {
		return this.codPue;
	}

	public void setCodPue(Integer codPue) {
		this.codPue = codPue;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}