package pe.lindley.puntointeres.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerClienteRequest {
	
	@SerializedName("COD")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
