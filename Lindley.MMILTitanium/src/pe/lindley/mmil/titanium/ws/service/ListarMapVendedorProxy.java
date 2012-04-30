package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ListarMapVendedorRequest;
import pe.lindley.mmil.titanium.ws.bean.ListarMapVendedorResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ListarMapVendedorProxy extends ProxyBase<ListarMapVendedorResponse> {

	@InjectResource(R.string.urlwsMMILMapVendedor)protected String urlWS;
	
	public String codigoDeposito;
	public String codigoVendedor;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarMapVendedor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarMapVendedorRequest listarMapVendedorRequest = new ListarMapVendedorRequest();
		listarMapVendedorRequest.codigoDeposito=codigoDeposito;
		listarMapVendedorRequest.codigoVendedor=codigoVendedor;
		
		String request = JSONHelper.serializar(listarMapVendedorRequest);
		return request;
		
	}

	@Override
	protected ListarMapVendedorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarMapVendedorResponse response = JSONHelper.desSerializar(json, ListarMapVendedorResponse.class);
		return response;
	}

}
