package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class TipoTransp implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_tip_trans")
	private Integer idTipTrans;

	@SerializedName("cod_tip_trans")
	private Integer codTipTrans;

	private String descripcion;

	public TipoTransp() {
	}

	public Integer getIdTipTrans() {
		return this.idTipTrans;
	}

	public void setIdTipTrans(Integer idTipTrans) {
		this.idTipTrans = idTipTrans;
	}

	public Integer getCodTipTrans() {
		return this.codTipTrans;
	}

	public void setCodTipTrans(Integer codTipTrans) {
		this.codTipTrans = codTipTrans;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}