package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class TratamientoAgua implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_tra_agu")
	private Integer idTraAgu;

	@SerializedName("cod_tra_agu")
	private Integer codTraAgu;

	private String descripcion;

	public TratamientoAgua() {
	}

	public Integer getIdTraAgu() {
		return this.idTraAgu;
	}

	public void setIdTraAgu(Integer idTraAgu) {
		this.idTraAgu = idTraAgu;
	}

	public Integer getCodTraAgu() {
		return this.codTraAgu;
	}

	public void setCodTraAgu(Integer codTraAgu) {
		this.codTraAgu = codTraAgu;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}