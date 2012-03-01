package lindley.desarrolloxcliente.ws.service;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarClienteRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarClienteResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarClienteProxy extends ProxyBase<ConsultarClienteResponse> {
	
	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;

	private String codigo;
	private String ruc;
	private String dni;
	private String razonSocial;
	
	public String getCodigo() {
		return codigo;
	} 

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarClienteRequest consultarClienteRequest = new ConsultarClienteRequest();
		consultarClienteRequest.setCodigo(this.codigo);
		consultarClienteRequest.setDni(this.dni);
		consultarClienteRequest.setRuc(this.ruc);
		consultarClienteRequest.setRazonSocial(this.razonSocial);
		
		String request = JSONHelper.serializar(consultarClienteRequest);
		return request;
		
	}

	@Override
	protected ConsultarClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarClienteResponse response = JSONHelper.desSerializar(json, ConsultarClienteResponse.class);
		return response;
	}
	
	
	
	@Override
	protected String getUrl() {
		return urlWS + "/ConsultarCliente";
	}

	
}
