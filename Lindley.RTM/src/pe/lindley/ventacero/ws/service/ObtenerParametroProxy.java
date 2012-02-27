package pe.lindley.ventacero.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.ventacero.ws.bean.ObtenerParametroResponse;
import pe.lindley.ventacero.ws.bean.ObtenerParametroRequest;
import roboguice.inject.InjectResource;

public class ObtenerParametroProxy extends ProxyBase<ObtenerParametroResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsVtaCeroParam)protected String urlWS;
	
	private String codigo;
	private String deposito;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ObtenerParametro";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerParametroRequest obtenerParametroRequest = new ObtenerParametroRequest();
		obtenerParametroRequest.setCodigo(codigo);
		obtenerParametroRequest.setDeposito(deposito);
		String request = JSONHelper.serializar(obtenerParametroRequest);
		return request;
	}

	@Override
	protected ObtenerParametroResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerParametroResponse obtenerParametroResponse = JSONHelper.desSerializar(json, ObtenerParametroResponse.class);
		return obtenerParametroResponse;
	}
	
}
