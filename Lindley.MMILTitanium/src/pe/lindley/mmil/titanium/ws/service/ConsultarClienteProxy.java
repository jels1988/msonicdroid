package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ConsultarClienteRequest;
import pe.lindley.mmil.titanium.ws.bean.ConsultarClienteResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarClienteProxy extends ProxyBase<ConsultarClienteResponse> {
	
	public String codigoDeposito;
	public String codigoCliente;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarClienteRequest consultarClienteRequest = new ConsultarClienteRequest();
		consultarClienteRequest.codigoDeposito=codigoDeposito;
		consultarClienteRequest.codigoCliente=codigoCliente;
		
		String request = JSONHelper.serializar(consultarClienteRequest);
		return request;
	}

	@Override
	protected ConsultarClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarClienteResponse response =  JSONHelper.desSerializar(json,ConsultarClienteResponse.class);
		return response;
	}

}
