package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarActividadRequest {
	
	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("PLAN")
	private String codigoPlan;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPlan() {
		return codigoPlan;
	}

	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}

}
