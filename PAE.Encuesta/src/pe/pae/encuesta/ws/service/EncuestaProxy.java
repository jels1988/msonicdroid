package pe.pae.encuesta.ws.service;


import pe.pae.encuesta.R;
import pe.pae.encuesta.ws.bean.EncuestaRequest;
import pe.pae.encuesta.ws.bean.EncuestaResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class EncuestaProxy extends ProxyBase<EncuestaResponse> {

	@InjectResource(R.string.urlLoginWS)protected String urlWS;

	
	public int usuarioId;
	public int clienteId;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarEncuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		EncuestaRequest encuestaRequest = new EncuestaRequest();
		encuestaRequest.clienteId=clienteId;
		encuestaRequest.usuarioId=usuarioId;
		String request = JSONHelper.serializar(encuestaRequest);
		return request;

	}

	@Override
	protected EncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		EncuestaResponse encuestaResponse = JSONHelper.desSerializar(json, EncuestaResponse.class);
		return encuestaResponse;
	}

}
