package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarSupervisorRequest {
	
	@SerializedName("COD")
	private String codigoDeposito;

	@SerializedName("TIP")
	private int tipo;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
