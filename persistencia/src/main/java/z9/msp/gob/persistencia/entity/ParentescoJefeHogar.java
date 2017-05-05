package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the parentesco_jefe_hogar database table.
 * 
 */
public class ParentescoJefeHogar implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_par_jh")
	private Integer idParJh;

	@SerializedName("cod_par_jh")
	private Integer codParJh;

	@SerializedName("descripcion")
	private String descripcion;

	public ParentescoJefeHogar() {
	}

	public Integer getIdParJh() {
		return this.idParJh;
	}

	public void setIdParJh(Integer idParJh) {
		this.idParJh = idParJh;
	}

	public Integer getCodParJh() {
		return this.codParJh;
	}

	public void setCodParJh(Integer codParJh) {
		this.codParJh = codParJh;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}