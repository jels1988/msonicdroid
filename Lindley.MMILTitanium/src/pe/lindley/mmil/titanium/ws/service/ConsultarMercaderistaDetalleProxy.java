package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ConsultarMercaderistaDetalleRequest;
import pe.lindley.mmil.titanium.ws.bean.ConsultarMercaderistaDetalleResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarMercaderistaDetalleProxy extends ProxyBase<ConsultarMercaderistaDetalleResponse> {

	public String codigoDeposito;
	public String codigoSupervisor;
	public String codigoMercaderista;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarMercaderistaDetalle";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarMercaderistaDetalleRequest consultarMercaderistaDetalleRequest = new ConsultarMercaderistaDetalleRequest();
		
		consultarMercaderistaDetalleRequest.codigoDeposito = codigoDeposito;
		consultarMercaderistaDetalleRequest.codigoSupervisor = codigoSupervisor;
		consultarMercaderistaDetalleRequest.codigoMercaderista = codigoMercaderista;
		
		String request = JSONHelper.serializar(consultarMercaderistaDetalleRequest);
		return request;
	}
	@Override
	protected ConsultarMercaderistaDetalleResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarMercaderistaDetalleResponse response =  JSONHelper.desSerializar(json,ConsultarMercaderistaDetalleResponse.class);
		return response;
	}

}
