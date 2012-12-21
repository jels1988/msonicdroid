package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarCompromisoRequest {
	
	@Expose()
	@SerializedName("CLI")
	private String codigoCliente;
	
	@Expose()
	@SerializedName("CODR")
	private String codigoRegistro;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoRegistro() { 
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

}
