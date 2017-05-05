package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_provincia")
	private Integer idProvincia;

	@SerializedName("cod_prov")
	private String codProv;

	private String descripcion;
	public Provincia() {
	}

	public Integer getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getCodProv() {
		return this.codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}