package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarPresentacionSugeridoRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarPresentacionSugeridoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarPresentacionProxy extends ProxyBase<ConsultarPresentacionSugeridoResponse> {

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
		return urlWS + "/ConsultarPresentacionSugerido";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarPresentacionSugeridoRequest consultarPresentacionSugeridoRequest = new ConsultarPresentacionSugeridoRequest();
		consultarPresentacionSugeridoRequest.setCodigoCliente(this.codigoCliente);
		
		String request = JSONHelper.serializar(consultarPresentacionSugeridoRequest);
		return request;
	}

	@Override
	protected ConsultarPresentacionSugeridoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPresentacionSugeridoResponse response = JSONHelper.desSerializar(json, ConsultarPresentacionSugeridoResponse.class);
		return response;
	}

}
