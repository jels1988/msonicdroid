package pe.lindley.puntointeres.ws.service;

import pe.lindley.puntointeres.ws.bean.ObtenerPuntoInteresResponse;
import pe.lindley.puntointeres.ws.bean.ObtenerPuntoInteresRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerPuntoInteresProxy extends ProxyBase<ObtenerPuntoInteresResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsPINT)protected String urlWS;
	
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
		return urlWS + "/ListarPtosInteres";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerPuntoInteresRequest obtenerPuntoInteresRequest = new ObtenerPuntoInteresRequest();
		obtenerPuntoInteresRequest.setCodigo(codigo);
		String request = JSONHelper.serializar(obtenerPuntoInteresRequest);
		return request;
	}

	@Override
	protected ObtenerPuntoInteresResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerPuntoInteresResponse obtenerPuntoInteresResponse = JSONHelper.desSerializar(json, ObtenerPuntoInteresResponse.class);
		return obtenerPuntoInteresResponse;
	}
	
}