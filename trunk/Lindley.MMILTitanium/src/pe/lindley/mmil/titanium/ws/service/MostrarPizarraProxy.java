package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.MostrarPizarraRequest;
import pe.lindley.mmil.titanium.ws.bean.MostrarPizarraResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class MostrarPizarraProxy extends ProxyBase<MostrarPizarraResponse> {
	
	public String codigoDeposito;
	
	@InjectResource(R.string.urlwsMMILPizarra)protected String urlWS;	
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPizarraCDA";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarPizarraRequest mostrarPizarraRequest = new MostrarPizarraRequest();
		mostrarPizarraRequest.codigoDeposito = codigoDeposito;
		
		String request = JSONHelper.serializar(mostrarPizarraRequest);
		return request;
	}

	@Override
	protected MostrarPizarraResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarPizarraResponse response = JSONHelper.desSerializar(json, MostrarPizarraResponse.class);
		return response;
	}

}
