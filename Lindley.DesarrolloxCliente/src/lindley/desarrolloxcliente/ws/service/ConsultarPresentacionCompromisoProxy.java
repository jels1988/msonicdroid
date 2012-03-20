package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarOportunidadResponse;
import lindley.desarrolloxcliente.ws.bean.ConsultarPresentacionCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarPresentacionCompromisoProxy extends ProxyBase<ConsultarPresentacionCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private String codigoCliente;	
	private String codigoRegistro;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoRegistro() { 
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarPresentacionCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarCompromisoRequest consultarCompromisoRequest = new ConsultarCompromisoRequest();
		consultarCompromisoRequest.setCodigoCliente(this.codigoCliente);
		consultarCompromisoRequest.setCodigoRegistro(this.codigoRegistro);
		
		String request = JSONHelper.serializar(consultarCompromisoRequest);
		return request;
	}

	@Override
	protected ConsultarPresentacionCompromisoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPresentacionCompromisoResponse response = JSONHelper.desSerializar(json, ConsultarPresentacionCompromisoResponse.class);
		return response;
	}

}
