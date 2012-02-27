package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarIndiceEjecucionAnioRequest {
	
	@SerializedName("AM")
	private String periodo;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
