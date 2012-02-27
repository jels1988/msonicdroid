package pe.lindley.prospector.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DatosClienteRequest {
	
	@SerializedName("Codigo")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
