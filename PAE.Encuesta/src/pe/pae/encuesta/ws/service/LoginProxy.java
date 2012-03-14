package pe.pae.encuesta.ws.service;

import pe.pae.encuesta.R;
import pe.pae.encuesta.ws.bean.LoginRequest;
import pe.pae.encuesta.ws.bean.LoginResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class LoginProxy extends  ProxyBase<LoginResponse>{

	@InjectResource(R.string.urlLoginWS)protected String urlWS;
	
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
		loginRequest.password=password;
		loginRequest.usuario=usuario;
		
		String request = JSONHelper.serializar(loginRequest);
		return request;
		
	}

	@Override
	protected LoginResponse responseText(String json) {
		// TODO Auto-generated method stub
		LoginResponse loginResponse = JSONHelper.desSerializar(json,LoginResponse.class);
		return loginResponse;
	}

}
