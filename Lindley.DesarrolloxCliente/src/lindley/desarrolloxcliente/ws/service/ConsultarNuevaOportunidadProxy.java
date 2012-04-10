package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarNuevaOportunidadResponse;
import lindley.desarrolloxcliente.ws.bean.ConsultarOportunidadRequest;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarNuevaOportunidadProxy extends ProxyBase<ConsultarNuevaOportunidadResponse> {

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
		return urlWS + "/ConsultarNuevaOportunidad";
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
	protected ConsultarNuevaOportunidadResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarNuevaOportunidadResponse response = JSONHelper.desSerializar(json, ConsultarNuevaOportunidadResponse.class);
		return response;
	}
	
}
