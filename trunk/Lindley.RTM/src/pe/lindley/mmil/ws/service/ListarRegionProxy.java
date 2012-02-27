package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ListarRegionRequest;
import pe.lindley.mmil.ws.bean.ListarRegionResponse;
import roboguice.inject.InjectResource;

public class ListarRegionProxy extends ProxyBase<ListarRegionResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILRegion) protected String urlWS;
	
	private String pais;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarRegions";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarRegionRequest listarRegionRequest = new ListarRegionRequest();
		listarRegionRequest.setPais(pais);
		String request = JSONHelper.serializar(listarRegionRequest);
		return request;
	}

	@Override
	protected ListarRegionResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarRegionResponse listarRegionResponse = JSONHelper.desSerializar(json, ListarRegionResponse.class);
		return listarRegionResponse;
	}
	
}
