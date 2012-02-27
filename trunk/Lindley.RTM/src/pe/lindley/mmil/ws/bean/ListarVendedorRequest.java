package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarVendedorRequest {
	
	@SerializedName("COD")
	private String codigoDeposito;

	@SerializedName("TIP")
	private String tipo;

	@SerializedName("SUP")
	private String codigoSupervisor;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigoSupervisor() {
		return codigoSupervisor;
	}

	public void setCodigoSupervisor(String codigoSupervisor) {
		this.codigoSupervisor = codigoSupervisor;
	}


}
