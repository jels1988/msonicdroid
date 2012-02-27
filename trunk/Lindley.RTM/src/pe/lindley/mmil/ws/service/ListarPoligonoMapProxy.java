package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ListarPoligonoMapRequest;
import pe.lindley.mmil.ws.bean.ListarPoligonoMapResponse;
import roboguice.inject.InjectResource;

public class ListarPoligonoMapProxy extends ProxyBase<ListarPoligonoMapResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILPoligonoMap)protected String urlWS;
	
	private String pais;
	private String region;
	private String deposito;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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
		return urlWS + "/ListarPoligonoMap";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarPoligonoMapRequest listarPoligonoMapRequest = new ListarPoligonoMapRequest();
		listarPoligonoMapRequest.setDeposito(deposito);
		listarPoligonoMapRequest.setPais(pais);
		listarPoligonoMapRequest.setRegion(region);
		String request = JSONHelper.serializar(listarPoligonoMapRequest);
		return request;
	}

	@Override
	protected ListarPoligonoMapResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarPoligonoMapResponse listarPoligonoMapResponse = JSONHelper.desSerializar(json, ListarPoligonoMapResponse.class);
		return listarPoligonoMapResponse;
	}
}
