package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerEncuestaResponse;
import pe.lindley.ficha.ws.bean.ObtenerEncuestaRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerEncuestaProxy extends ProxyBase<ObtenerEncuestaResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsFichaEncuesta)protected String urlWS;
	
	private String Opcion;
	private String CodEncuesta;

	public String getOpcion() {
		return Opcion;
	}

	public void setOpcion(String opcion) {
		Opcion = opcion;
	}

	public String getCodEncuesta() {
		return CodEncuesta;
	}

	public void setCodEncuesta(String codEncuesta) {
		CodEncuesta = codEncuesta;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/obtenerEncuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerEncuestaRequest obtenerEncuestaRequest = new ObtenerEncuestaRequest();
		obtenerEncuestaRequest.setCodigoEncuesta(CodEncuesta);
		obtenerEncuestaRequest.setOpcion(Opcion);
		String request = JSONHelper.serializar(obtenerEncuestaRequest);
		return request;
	}

	@Override
	protected ObtenerEncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerEncuestaResponse obtenerEncuestaResponse = JSONHelper.desSerializar(json, ObtenerEncuestaResponse.class);
		return obtenerEncuestaResponse;
	}

}
