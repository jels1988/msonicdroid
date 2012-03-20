package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarPosicionSugeridoRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarPosicionSugeridoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarPosicionProxy extends ProxyBase<ConsultarPosicionSugeridoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private String codigoCliente;
	private String respuesta;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarPosicionSugerido";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarPosicionSugeridoRequest consultarPosicionSugeridoRequest = new ConsultarPosicionSugeridoRequest();
		consultarPosicionSugeridoRequest.setCodigoCliente(this.codigoCliente);
		consultarPosicionSugeridoRequest.setRespuesta(this.respuesta);
		
		String request = JSONHelper.serializar(consultarPosicionSugeridoRequest);
		return request;
	}

	@Override
	protected ConsultarPosicionSugeridoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPosicionSugeridoResponse response = JSONHelper.desSerializar(json, ConsultarPosicionSugeridoResponse.class);
		return response;
	}

}
