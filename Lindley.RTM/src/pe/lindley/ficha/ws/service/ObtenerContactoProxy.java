package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerContactoResponse;
import pe.lindley.ficha.ws.bean.ObtenerContactoRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerContactoProxy extends ProxyBase<ObtenerContactoResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaContacto)protected String urlWS;
	
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarContacto";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerContactoRequest obtenerContactoRequest = new ObtenerContactoRequest();
		obtenerContactoRequest.setCodigo(codigo);
		String request = JSONHelper.serializar(obtenerContactoRequest);
		return request;
	}

	@Override
	protected ObtenerContactoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerContactoResponse obtenerContactoResponse = JSONHelper.desSerializar(json, ObtenerContactoResponse.class);
		return obtenerContactoResponse;
	}
	
}
