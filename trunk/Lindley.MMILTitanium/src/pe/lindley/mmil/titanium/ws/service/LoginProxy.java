package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.LoginRequest;
import pe.lindley.mmil.titanium.ws.bean.LoginResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class LoginProxy extends ProxyBase<LoginResponse> {

	@InjectResource(R.string.urlwsLogin)protected String urlWS;
	
	public String usuario;
	public String password;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ValidarUsuario";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.password = password;
		loginRequest.usuario = usuario;
		String request = JSONHelper.serializar(loginRequest);
		return request;
	}

	@Override
	protected LoginResponse responseText(String json) {
		// TODO Auto-generated method stub
		LoginResponse response = JSONHelper.desSerializar(json, LoginResponse.class);
		return response;
	}

}
