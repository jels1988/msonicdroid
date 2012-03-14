package pe.pae.encuesta.ws.service;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
	
	@SerializedName("usr")
	public String usuario;
	
	@SerializedName("pwd")
	public String password;
}
