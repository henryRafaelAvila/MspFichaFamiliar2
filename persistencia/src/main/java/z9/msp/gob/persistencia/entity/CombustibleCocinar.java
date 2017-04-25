package z9.msp.gob.persistencia.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * The persistent class for the combustible_cocinar database table.
 * 
 */
public class CombustibleCocinar implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("id_comb_coc")
	private Integer idCombCoc;

	@SerializedName("cod_coc")
	private Integer codCoc;

	private String descripcion;

	public CombustibleCocinar() {
	}

	public Integer getIdCombCoc() {
		return this.idCombCoc;
	}

	public void setIdCombCoc(Integer idCombCoc) {
		this.idCombCoc = idCombCoc;
	}

	public Integer getCodCoc() {
		return this.codCoc;
	}

	public void setCodCoc(Integer codCoc) {
		this.codCoc = codCoc;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}