package pe.lindley.puntointeres.to;

import com.google.gson.annotations.SerializedName;

public class SubGiroTO {
	
	@SerializedName("SGR")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
