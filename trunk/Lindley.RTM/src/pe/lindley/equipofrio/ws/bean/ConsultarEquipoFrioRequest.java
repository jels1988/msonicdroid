package pe.lindley.equipofrio.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarEquipoFrioRequest {

	@SerializedName("Codigo")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
