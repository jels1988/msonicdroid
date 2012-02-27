package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarGeneradorRequest;
import pe.lindley.red.ws.bean.ConsultarGeneradorResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarGeneradorProxy extends ProxyBase<ConsultarGeneradorResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;

	private String codigoCliente;
	
	private String anioMes;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(String anioMes) {
		this.anioMes = anioMes;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarGenerador";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarGeneradorRequest consultarGeneradorRequest = new ConsultarGeneradorRequest();
		consultarGeneradorRequest.setAnioMes(this.anioMes);
		consultarGeneradorRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarGeneradorRequest);
		return request;
	}

	@Override
	protected ConsultarGeneradorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarGeneradorResponse response = JSONHelper.desSerializar(json,ConsultarGeneradorResponse.class);
		return response;
	}

}
