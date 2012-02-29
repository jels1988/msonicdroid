package lindley.desarrolloxcliente.ws.service;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.LoginRequest;
import lindley.desarrolloxcliente.ws.bean.LoginResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import roboguice.inject.InjectResource;


public class LoginProxy extends ProxyBase<LoginResponse> {
	
	@InjectResource(R.string.urlwsLogin)protected String urlWS;

	private String usuario;
	private String password;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String requestText() {
		// TODO Auto-generated method stub
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setPassword(password);
		loginRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(loginRequest);
		return request;
	}
	
	@Override
	protected LoginResponse responseText(String json) {
		// TODO Auto-generated method stub
		LoginResponse response = JSONHelper.desSerializar(json, LoginResponse.class);
		return response;
	}
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ValidarUsuario";
	}
	
}

