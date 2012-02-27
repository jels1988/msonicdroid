package pe.lindley.profit.to;

import com.google.gson.annotations.SerializedName;

public class Articulo {

	@SerializedName("ART")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
