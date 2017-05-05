package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class TipoVivienda implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_tip_viv_id")
	private Integer idTipVivId;

	@SerializedName("cod_tip_viv")
	private Integer codTipViv;

	private String descripcion;

	public TipoVivienda() {
	}

	public Integer getIdTipVivId() {
		return this.idTipVivId;
	}

	public void setIdTipVivId(Integer idTipVivId) {
		this.idTipVivId = idTipVivId;
	}

	public Integer getCodTipViv() {
		return this.codTipViv;
	}

	public void setCodTipViv(Integer codTipViv) {
		this.codTipViv = codTipViv;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}