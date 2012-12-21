package lindley.desarrolloxcliente.ws.bean;

import lindley.desarrolloxcliente.to.UsuarioTO;
import net.msonic.lib.ResponseBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ResponseBase {

	
	@Expose()
	@SerializedName("Usuario")
	private UsuarioTO usuario;
	
	public UsuarioTO getUsuario() {
		return usuario;
	}
	
}
