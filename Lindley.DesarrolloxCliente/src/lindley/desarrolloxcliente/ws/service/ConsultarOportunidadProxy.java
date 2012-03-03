package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarOportunidadRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarOportunidadResponse;

public class ConsultarOportunidadProxy extends ProxyBase<ConsultarOportunidadResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private String codigoCliente;
	private String tipoOportunidad;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	} 
	
	public String getTipoOportunidad() {
		return tipoOportunidad;
	}

	public void setTipoOportunidad(String tipoOportunidad) {
		this.tipoOportunidad = tipoOportunidad;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarOportunidad";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarOportunidadRequest consultarOportunidadRequest = new ConsultarOportunidadRequest();
		consultarOportunidadRequest.setCodigoCliente(this.codigoCliente);
		consultarOportunidadRequest.setTipoOportunidad(this.tipoOportunidad);
		
		String request = JSONHelper.serializar(consultarOportunidadRequest);
		return request;
	}

	@Override
	protected ConsultarOportunidadResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarOportunidadResponse response = JSONHelper.desSerializar(json, ConsultarOportunidadResponse.class);
		return response;
	}

}
