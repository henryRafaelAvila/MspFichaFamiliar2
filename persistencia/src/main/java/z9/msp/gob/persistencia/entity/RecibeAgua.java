package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class RecibeAgua implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_rec_agu")
	private Integer idRecAgu;

	@SerializedName("cod_rec_agu")
	private Integer codRecAgu;

	private String descripcion;

	public RecibeAgua() {
	}

	public Integer getIdRecAgu() {
		return this.idRecAgu;
	}

	public void setIdRecAgu(Integer idRecAgu) {
		this.idRecAgu = idRecAgu;
	}

	public Integer getCodRecAgu() {
		return this.codRecAgu;
	}

	public void setCodRecAgu(Integer codRecAgu) {
		this.codRecAgu = codRecAgu;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}