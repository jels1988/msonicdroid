package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ListarVendedorRequest;
import pe.lindley.mmil.titanium.ws.bean.ListarVendedorResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;



public class ListarVendedorProxy extends ProxyBase<ListarVendedorResponse> {


	@InjectResource(R.string.urlwsMMILVendedor)protected String urlWS;
	
	public String codigoDeposito;
	public String tipo;
	public String codigoSupervisor;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarVendedor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarVendedorRequest listarVendedorRequest = new ListarVendedorRequest();
		listarVendedorRequest.codigoDeposito = codigoDeposito;
		listarVendedorRequest.tipo=tipo;
		listarVendedorRequest.codigoSupervisor=codigoSupervisor;
		String request = JSONHelper.serializar(listarVendedorRequest);
		return request;
	}

	@Override
	protected ListarVendedorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarVendedorResponse listarVendedorResponse =  JSONHelper.desSerializar(json,ListarVendedorResponse.class);
		return listarVendedorResponse;
	}

}
