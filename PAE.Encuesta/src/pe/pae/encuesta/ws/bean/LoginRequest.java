package pe.pae.encuesta.ws.bean;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
	
	@SerializedName("usr")
	public String usuario;
	
	@SerializedName("pwd")
	public String password;
}
