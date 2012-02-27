package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerVisualizacionResponse;
import pe.lindley.ficha.ws.bean.ObtenerVisualizacionRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerVisualizacionProxy extends ProxyBase<ObtenerVisualizacionResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaVisualizacion)protected String urlWS;
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
		return urlWS + "/ListarVisualizacion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerVisualizacionRequest obtenerVisualizacionRequest = new ObtenerVisualizacionRequest();
		obtenerVisualizacionRequest.setCodigo(codigo);
		String request = JSONHelper.serializar(obtenerVisualizacionRequest);
		return request;
	}

	@Override
	protected ObtenerVisualizacionResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerVisualizacionResponse ObtenerVisualizacionResponse = JSONHelper.desSerializar(json, ObtenerVisualizacionResponse.class);
		return ObtenerVisualizacionResponse;
	}
	
}
