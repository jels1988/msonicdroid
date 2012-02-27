package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerClienteRequest {
		
	@SerializedName("CLI")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCliente(String codigo) {
		this.codigo = codigo;
	}


}
