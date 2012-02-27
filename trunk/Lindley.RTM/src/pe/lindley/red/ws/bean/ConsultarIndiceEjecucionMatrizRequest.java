package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarIndiceEjecucionMatrizRequest {


	@SerializedName("AM")
	private String anioMes;

	public String getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(String anioMes) {
		this.anioMes = anioMes;
	}
}
