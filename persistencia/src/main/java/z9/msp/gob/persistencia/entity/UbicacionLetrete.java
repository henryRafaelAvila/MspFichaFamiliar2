package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class UbicacionLetrete implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_ubi_let")
	private Integer idUbiLet;

	@SerializedName("cod_ubi_let")
	private Integer codUbiLet;

	private String descripcion;

	public UbicacionLetrete() {
	}

	public Integer getIdUbiLet() {
		return this.idUbiLet;
	}

	public void setIdUbiLet(Integer idUbiLet) {
		this.idUbiLet = idUbiLet;
	}

	public Integer getCodUbiLet() {
		return this.codUbiLet;
	}

	public void setCodUbiLet(Integer codUbiLet) {
		this.codUbiLet = codUbiLet;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}