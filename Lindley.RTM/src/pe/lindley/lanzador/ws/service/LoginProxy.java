package pe.lindley.lanzador.ws.service;

import pe.lindley.lanzador.ws.bean.LoginRequest;
import pe.lindley.lanzador.ws.bean.LoginResponse;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;


public class LoginProxy extends ProxyBase<LoginResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsLogin)protected String urlWS;

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
		String request = pe.lindley.util.JSONHelper.serializar(loginRequest);
		return request;
	}
	
	@Override
	protected LoginResponse responseText(String json) {
		// TODO Auto-generated method stub
		LoginResponse response = pe.lindley.util.JSONHelper.desSerializar(json, LoginResponse.class);
		return response;
	}
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ValidarUsuario";
	}
	
}

