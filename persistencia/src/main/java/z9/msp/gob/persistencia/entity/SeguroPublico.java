package z9.msp.gob.persistencia.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the seguro_publico database table.
 * 
 */
public class SeguroPublico implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_seg_pub")
	private Integer idSegPub;

	@SerializedName("cod_seg_pub")
	private Integer codSegPub;

	@SerializedName("descripcion")
	private String descripcion;

	public SeguroPublico() {
	}

	public Integer getIdSegPub() {
		return this.idSegPub;
	}

	public void setIdSegPub(Integer idSegPub) {
		this.idSegPub = idSegPub;
	}

	public Integer getCodSegPub() {
		return this.codSegPub;
	}

	public void setCodSegPub(Integer codSegPub) {
		this.codSegPub = codSegPub;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}