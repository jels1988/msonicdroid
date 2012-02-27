package pe.lindley.sacc.ws.service;

import com.google.gson.annotations.SerializedName;
import pe.lindley.sacc.ws.bean.ObtenerEventoRequest;
import pe.lindley.sacc.ws.bean.ObtenerEventoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerEventoProxy extends ProxyBase<ObtenerEventoResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlswsSACC)protected String urlWS;

	@SerializedName("CTC")
	private int idContacto;

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarEventoContacto";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerEventoRequest obtenerEventoRequest = new ObtenerEventoRequest();
		obtenerEventoRequest.setIdContacto(this.idContacto);
		String request = JSONHelper.serializar(obtenerEventoRequest);
		return request;
	}

	@Override
	protected ObtenerEventoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerEventoResponse obtenerEventoResponse = JSONHelper.desSerializar(json, ObtenerEventoResponse.class);
		return obtenerEventoResponse;
	}
	
}
