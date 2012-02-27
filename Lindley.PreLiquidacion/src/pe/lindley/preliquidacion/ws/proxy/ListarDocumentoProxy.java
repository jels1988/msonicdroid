package pe.lindley.preliquidacion.ws.proxy;

import pe.lindley.preliquidacion.R;
import pe.lindley.preliquidacion.ws.bean.ListarDocumentoRequest;
import pe.lindley.preliquidacion.ws.bean.ListarDocumentoResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ListarDocumentoProxy extends ProxyBase<ListarDocumentoResponse> {

	@InjectResource(R.string.urlwsDocumento)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarDocumento";
	}
	
	private String numeroCarga;
	public String getNumeroCarga() {
		return numeroCarga;
	}

	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}

	public String getDesposito() {
		return desposito;
	}

	public void setDesposito(String desposito) {
		this.desposito = desposito;
	}

	private String desposito;
	
	
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		ListarDocumentoRequest documentoRequest = new ListarDocumentoRequest();
		documentoRequest.setDesposito(desposito);
		documentoRequest.setNumeroCarga(numeroCarga);
		
		String request = JSONHelper.serializar(documentoRequest);
		
		return request;
	}

	@Override
	protected ListarDocumentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarDocumentoResponse response = JSONHelper.desSerializar(json,ListarDocumentoResponse.class);
		return response;
	}
	

}
