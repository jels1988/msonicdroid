package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.activity.R;
import pe.lindley.mmil.ws.bean.ListarCdaRequest;
import pe.lindley.mmil.ws.bean.ListarCdaResponse;
import roboguice.inject.InjectResource;

public class ListarCdaProxy extends ProxyBase<ListarCdaResponse>{
	
	@InjectResource(R.string.urlwsMMILCda)protected String urlWS;
	
	private String codigoRegion;
	
	
	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarCdasRegion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarCdaRequest listarCdaRequest = new ListarCdaRequest();
		listarCdaRequest.setCodigoRegion(codigoRegion);
		String request = JSONHelper.serializar(listarCdaRequest);
		return request;
	}

	@Override
	protected ListarCdaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarCdaResponse listarCdaResponse  = JSONHelper.desSerializar(json,ListarCdaResponse.class);
		return listarCdaResponse;
	}
	
}
