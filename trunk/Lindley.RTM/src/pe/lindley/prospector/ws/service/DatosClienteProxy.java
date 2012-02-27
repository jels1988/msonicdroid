package pe.lindley.prospector.ws.service;

import pe.lindley.prospector.ws.bean.DatosClienteRequest;
import pe.lindley.prospector.ws.bean.DatosClienteResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DatosClienteProxy extends ProxyBase<DatosClienteResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;
	
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
		DatosClienteRequest datosClienteRequest = new DatosClienteRequest();
		datosClienteRequest.setCodigo(this.codigo);
		String request = JSONHelper.serializar(datosClienteRequest);
		return request;
	}

	@Override
	protected DatosClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		DatosClienteResponse response = JSONHelper.desSerializar(json,DatosClienteResponse.class);
		return response;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GetCliente";
	}

}
