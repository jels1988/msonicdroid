package pe.lindley.preliquidacion.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.preliquidacion.to.UsuarioTO;
import net.msonic.lib.ResponseBase;

public class ValidarUsuarioResponse extends ResponseBase {

	@SerializedName("DAT")
	private UsuarioTO usuario;

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	
}
