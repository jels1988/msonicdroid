package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ClienteCreditosResponse;
import pe.lindley.mmil.titanium.ws.bean.ResumenVentaRequest;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ClientesCreditosProxy extends ProxyBase<ClienteCreditosResponse> {

	public String codigoDeposito;
	public String codigoSupervisor;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ClienteCreditos";
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
	protected ClienteCreditosResponse responseText(String json) {
		ClienteCreditosResponse response = JSONHelper.desSerializar(json, ClienteCreditosResponse.class);
		// TODO Auto-generated method stub
		return response;
	}

}
