package pe.lindley.mmil.titanium.ws.bean;



import pe.lindley.mmil.titanium.to.UsuarioTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class LoginResponse extends ResponseBase {

	
	@SerializedName("Usuario")
	public UsuarioTO usuario;
	
}
