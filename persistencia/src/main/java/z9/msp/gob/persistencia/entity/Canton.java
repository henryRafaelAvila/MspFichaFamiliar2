package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class Canton implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_canton")
	private Integer idCanton;


	@SerializedName("cod_cant")
	private String codCant;

	private String descripcion;


	@SerializedName("id_provincia")
	private Integer provincia_id;


	@SerializedName("id_admin")
	private Integer id_admin;
	

	public Canton() {
	}

	public Integer getId_admin() {
		return id_admin;
	}

	public void setId_admin(Integer id_admin) {
		this.id_admin = id_admin;
	}

	public Integer getIdCanton() {
		return this.idCanton;
	}

	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

	public String getCodCant() {
		return this.codCant;
	}

	public void setCodCant(String codCant) {
		this.codCant = codCant;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getProvincia_id() {
		return provincia_id;
	}

	public void setProvincia_id(Integer provincia_id) {
		this.provincia_id = provincia_id;
	}

}