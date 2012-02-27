package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerComercialRequest {
	
	@SerializedName("CLI")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
