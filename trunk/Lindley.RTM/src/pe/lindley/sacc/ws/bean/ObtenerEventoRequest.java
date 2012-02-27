package pe.lindley.sacc.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerEventoRequest {
	
	@SerializedName("CTC")
	private int idContacto;

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

}
