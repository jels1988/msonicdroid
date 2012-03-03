package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarOportunidadRequest {

	@SerializedName("COD")
	private String codigoCliente;
	
	@SerializedName("TIP")
	private String tipoOportunidad;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoOportunidad() {
		return tipoOportunidad;
	}

	public void setTipoOportunidad(String tipoOportunidad) {
		this.tipoOportunidad = tipoOportunidad;
	}
}
 