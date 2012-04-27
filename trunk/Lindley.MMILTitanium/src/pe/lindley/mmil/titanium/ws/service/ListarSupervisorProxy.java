package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ListarSupervisorResponse;
import pe.lindley.mmil.titanium.ws.bean.ListarSupervisorRequest;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;


public class ListarSupervisorProxy extends ProxyBase<ListarSupervisorResponse> {

	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	public String codigoDeposito;
	public int tipo;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarSupervisor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		ListarSupervisorRequest listarSupervisorRequest = new ListarSupervisorRequest();
		listarSupervisorRequest.codigoDeposito=codigoDeposito;
		listarSupervisorRequest.tipo=tipo;
		
		String request = JSONHelper.serializar(listarSupervisorRequest);
		return request;
		
	}

	@Override
	protected ListarSupervisorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarSupervisorResponse response = JSONHelper.desSerializar(json, ListarSupervisorResponse.class);
		return response;
	}

}
