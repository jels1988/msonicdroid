package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class CerrarCompromisoProxy extends ProxyBase<CerrarCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	private String codigoCabecera;

	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CerrarCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		CerrarCompromisoRequest cerrarCompromisoRequest = new CerrarCompromisoRequest();
		cerrarCompromisoRequest.setCodigoCabecera(this.codigoCabecera);
		String request = JSONHelper.serializar(cerrarCompromisoRequest);
		return request;
	}

	@Override
	protected CerrarCompromisoResponse responseText(String json) {
		// TODO Auto-generated method stub
		CerrarCompromisoResponse response = JSONHelper.desSerializar(json, CerrarCompromisoResponse.class);
		return response;
	}

}
