package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarFichaRequest {

	@SerializedName("CCL")
	private String codigoCliente;
	
	@SerializedName("AMD")
	private String fechaEncuesta;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getFechaEncuesta() {
		return fechaEncuesta;
	}

	public void setFechaEncuesta(String fechaEncuesta) {
		this.fechaEncuesta = fechaEncuesta;
	}
	
}
