package pe.lindley.ventacero.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerParametroRequest {
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("CDA")
	private String deposito;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
}
