package pe.pae.encuesta.ws.service;

import java.util.ArrayList;

import pe.pae.encuesta.R;
import pe.pae.encuesta.to.RespuestaDataTO;
import pe.pae.encuesta.ws.bean.GuardarRespuestaRequest;
import pe.pae.encuesta.ws.bean.GuardarRespuestaResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class GuardarRespuestaProxy extends ProxyBase<GuardarRespuestaResponse> {

	@InjectResource(R.string.urlLoginWS)protected String urlWS;
	
	public ArrayList<RespuestaDataTO> respuestas;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/SaveRespuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		GuardarRespuestaRequest guardarRespuestaRequest = new GuardarRespuestaRequest();
		guardarRespuestaRequest.respuestas = respuestas;
		
		String request = JSONHelper.serializar(guardarRespuestaRequest);
		return request;
		
	}

	@Override
	protected GuardarRespuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarRespuestaResponse guardarRespuestaResponse = JSONHelper.desSerializar(json, GuardarRespuestaResponse.class);
		return guardarRespuestaResponse;
	}
	

}
