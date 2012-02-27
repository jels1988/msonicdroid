package pe.lindley.lanzador.ws.bean;

import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ResponseBase {

	
	@SerializedName("Usuario")
	private UsuarioTO usuario;
	
	public UsuarioTO getUsuario() {
		return usuario;
	}
	
}
