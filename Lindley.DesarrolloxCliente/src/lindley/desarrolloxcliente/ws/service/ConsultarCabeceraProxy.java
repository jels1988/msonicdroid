package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarCabeceraResponse;
import lindley.desarrolloxcliente.ws.bean.ConsultarCabeceraRequest;

public class ConsultarCabeceraProxy extends ProxyBase<ConsultarCabeceraResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private String codigoCliente;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarCabecera";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarCabeceraRequest consultarCabeceraRequest = new ConsultarCabeceraRequest();
		consultarCabeceraRequest.setCodigoCliente(this.codigoCliente);
		
		String request = JSONHelper.serializar(consultarCabeceraRequest);
		return request;
	}

	@Override
	protected ConsultarCabeceraResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarCabeceraResponse response = JSONHelper.desSerializar(json, ConsultarCabeceraResponse.class);
		return response;
	}

}
