package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ViasAcceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_via_acc")
	private Integer idViaAcc;

	@SerializedName("cod_via_acc")
	private Integer codViaAcc;

	private String descripcion;

	public ViasAcceso() {
	}

	public Integer getIdViaAcc() {
		return this.idViaAcc;
	}

	public void setIdViaAcc(Integer idViaAcc) {
		this.idViaAcc = idViaAcc;
	}

	public Integer getCodViaAcc() {
		return this.codViaAcc;
	}

	public void setCodViaAcc(Integer codViaAcc) {
		this.codViaAcc = codViaAcc;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}