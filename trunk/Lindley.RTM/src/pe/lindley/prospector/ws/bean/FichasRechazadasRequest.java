package pe.lindley.prospector.ws.bean;

import com.google.gson.annotations.SerializedName;

public class FichasRechazadasRequest {
	
	@SerializedName("Usuario")
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
