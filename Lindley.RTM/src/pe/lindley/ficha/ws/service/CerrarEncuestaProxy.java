package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.GuardarEncuestaRequest;
import pe.lindley.ficha.ws.bean.GuardarEncuestaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class CerrarEncuestaProxy extends ProxyBase<GuardarEncuestaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaEncuesta)protected String urlWS;
	
	private String codigoEncuesta;
	private String usuario;

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/cerrarEncuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarEncuestaRequest guardarEncuestaRequest = new GuardarEncuestaRequest();
		guardarEncuestaRequest.setCodigoEncuesta(codigoEncuesta);
		guardarEncuestaRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarEncuestaRequest);
		return request;
	}

	@Override
	protected GuardarEncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarEncuestaResponse guardarEncuestaResponse = JSONHelper.desSerializar(json, GuardarEncuestaResponse.class); 
		return guardarEncuestaResponse;
	}
}