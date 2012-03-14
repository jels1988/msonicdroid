package pe.pae.encuesta.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.pae.encuesta.to.ClienteTO;
import pe.pae.encuesta.to.UsuarioTO;
import net.msonic.lib.ResponseBase;

public class LoginResponse extends ResponseBase {

	
	@SerializedName("usr")
	public UsuarioTO usuario;
	
	@SerializedName("cli")
	public List<ClienteTO> clientes;
}
