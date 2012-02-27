package pe.lindley.puntointeres.ws.service;

import pe.lindley.puntointeres.ws.bean.ObtenerClienteResponse;
import pe.lindley.puntointeres.ws.bean.ObtenerClienteRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerClienteProxy extends ProxyBase<ObtenerClienteResponse>{

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
		return urlWS + "/ObtenerCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerClienteRequest obtenerClienteRequest = new ObtenerClienteRequest();
		obtenerClienteRequest.setCodigo(codigo);
		String request = JSONHelper.serializar(obtenerClienteRequest);
		return request;
	}

	@Override
	protected ObtenerClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerClienteResponse obtenerClienteResponse = JSONHelper.desSerializar(json, ObtenerClienteResponse.class);
		return obtenerClienteResponse;
	}
	
}
