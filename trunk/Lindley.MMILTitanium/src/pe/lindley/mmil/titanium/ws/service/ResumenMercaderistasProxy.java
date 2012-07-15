package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ConsultarMercaderistaResponse;
import pe.lindley.mmil.titanium.ws.bean.ResumenVentaRequest;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ResumenMercaderistasProxy extends ProxyBase<ConsultarMercaderistaResponse> {

	public String codigoDeposito;
	public String codigoSupervisor;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarMercaderista";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ResumenVentaRequest resumenVentaRequest = new ResumenVentaRequest();
		resumenVentaRequest.codigoDeposito = codigoDeposito;
		resumenVentaRequest.codigoSupervisor = codigoSupervisor;
		
		String request = JSONHelper.serializar(resumenVentaRequest);
		return request;
	}

	@Override
	protected ConsultarMercaderistaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarMercaderistaResponse response =  JSONHelper.desSerializar(json,ConsultarMercaderistaResponse.class);
		return response;
	}

}
