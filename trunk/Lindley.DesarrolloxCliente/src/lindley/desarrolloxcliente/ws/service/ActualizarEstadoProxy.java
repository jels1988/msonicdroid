package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ActualizarEstadoRequest;
import lindley.desarrolloxcliente.ws.bean.ActualizarEstadoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ActualizarEstadoProxy  extends ProxyBase<ActualizarEstadoResponse>{

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	public String codigo;
	public String estado;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CambiarEstado";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarEstadoRequest actualizarEstadoRequest = new ActualizarEstadoRequest();
		actualizarEstadoRequest.codigo = this.codigo;
		actualizarEstadoRequest.estado = this.estado;
		
		String request = JSONHelper.serializar(actualizarEstadoRequest);
		return request;
	}

	@Override
	protected ActualizarEstadoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarEstadoResponse response = JSONHelper.desSerializar(json, ActualizarEstadoResponse.class);
		return response;
	}

}
