package pe.lindley.equipofrio.ws.service;

import pe.lindley.equipofrio.ws.bean.ConsultarEquipoFrioRequest;
import pe.lindley.equipofrio.ws.bean.ConsultarEquipoFrioResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EquiposFrioProxy extends ProxyBase<ConsultarEquipoFrioResponse> {


	@InjectResource(pe.lindley.activity.R.string.urlwsEquipoFrio)protected String urlWS;
	
	private String codigo;
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarEquipoFrioRequest consultarEquipoFrioRequest = new ConsultarEquipoFrioRequest();
		consultarEquipoFrioRequest.setCodigo(this.codigo);
		String request = JSONHelper.serializar(consultarEquipoFrioRequest);
		return request;
	}

	@Override
	protected ConsultarEquipoFrioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarEquipoFrioResponse response = JSONHelper.desSerializar(json,ConsultarEquipoFrioResponse.class);
		return response;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarEquiposFrio";
	}
}
