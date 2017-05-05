package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ProcedenciaAgua implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_pro_agudd")
	private Integer idProAgudd;

	@SerializedName("cod_pro_agu")
	private Integer codProAgu;

	private String descripcion;

	public ProcedenciaAgua() {
	}

	public Integer getIdProAgudd() {
		return this.idProAgudd;
	}

	public void setIdProAgudd(Integer idProAgudd) {
		this.idProAgudd = idProAgudd;
	}

	public Integer getCodProAgu() {
		return this.codProAgu;
	}

	public void setCodProAgu(Integer codProAgu) {
		this.codProAgu = codProAgu;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}