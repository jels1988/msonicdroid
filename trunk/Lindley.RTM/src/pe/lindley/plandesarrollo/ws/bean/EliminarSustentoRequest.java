package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class EliminarSustentoRequest {
	
	@SerializedName("CSUS")
	private String codigoSustento;
	
	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("CPL")
	private String codigoPLan;
	
	@SerializedName("CACT")
	private String codigoActvidad;

	public String getCodigoSustento() {
		return codigoSustento;
	}

	public void setCodigoSustento(String codigoSustento) {
		this.codigoSustento = codigoSustento;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPLan() {
		return codigoPLan;
	}

	public void setCodigoPLan(String codigoPLan) {
		this.codigoPLan = codigoPLan;
	}

	public String getCodigoActvidad() {
		return codigoActvidad;
	}

	public void setCodigoActvidad(String codigoActvidad) {
		this.codigoActvidad = codigoActvidad;
	}

}
