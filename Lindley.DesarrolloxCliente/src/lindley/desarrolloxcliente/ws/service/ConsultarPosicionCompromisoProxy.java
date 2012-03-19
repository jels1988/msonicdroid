package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarOportunidadResponse;
import lindley.desarrolloxcliente.ws.bean.ConsultarPosicionCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarPosicionCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarPosicionCompromisoProxy extends ProxyBase<ConsultarPosicionCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private String codigoCliente;
	private String codigoGestion;
	//private String respuesta;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoGestion() {
		return codigoGestion;
	}

	public void setCodigoGestion(String codigoGestion) {
		this.codigoGestion = codigoGestion;
	}

	/*public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}*/
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarPosicionSugerido";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarPosicionCompromisoRequest consultarPosicionCompromisoRequest = new ConsultarPosicionCompromisoRequest();
		consultarPosicionCompromisoRequest.setCodigoCliente(this.codigoCliente);
		consultarPosicionCompromisoRequest.setCodigoGestion(this.codigoGestion);
		//consultarPosicionCompromisoRequest.setRespuesta(this.respuesta);
		
		String request = JSONHelper.serializar(consultarPosicionCompromisoRequest);
		return request;
	}

	@Override
	protected ConsultarPosicionCompromisoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPosicionCompromisoResponse response = JSONHelper.desSerializar(json, ConsultarOportunidadResponse.class);
		return response;
	}

}
