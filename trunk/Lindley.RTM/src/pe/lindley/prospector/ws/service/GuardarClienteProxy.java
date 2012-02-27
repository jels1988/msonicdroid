package pe.lindley.prospector.ws.service;


import java.util.ArrayList;

import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.ws.bean.GuardarClienteRequest;
import pe.lindley.prospector.ws.bean.GuardarClienteResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarClienteProxy extends ProxyBase<GuardarClienteResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;
	
	private ArrayList<ClienteTO> clientes;
	
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarClientes";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarClienteRequest guardarClienteRequest = new GuardarClienteRequest();
		guardarClienteRequest.setClientes(clientes);
		String request = JSONHelper.serializar(guardarClienteRequest);
		return request;
	}

	@Override
	protected GuardarClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarClienteResponse response = JSONHelper.desSerializar(json,GuardarClienteResponse.class);
		return response;
		
	}

	public void setClientes(ArrayList<ClienteTO> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<ClienteTO> getClientes() {
		return clientes;
	}

}
