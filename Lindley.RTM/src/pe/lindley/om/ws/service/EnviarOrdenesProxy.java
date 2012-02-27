package pe.lindley.om.ws.service;

import java.util.ArrayList;

import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.om.ws.bean.EnviarOrdenesRequest;
import pe.lindley.om.ws.bean.EnviarOrdenesResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EnviarOrdenesProxy  extends ProxyBase<EnviarOrdenesResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsOMParametros)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarOrdenes";
	}

	private ArrayList<OrdenTrabajoTO> ordenes;
	
	public ArrayList<OrdenTrabajoTO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<OrdenTrabajoTO> ordenes) {
		this.ordenes = ordenes;
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		EnviarOrdenesRequest enviarOrdenesRequest = new EnviarOrdenesRequest();
		enviarOrdenesRequest.setOrdenes(ordenes);
		String request = JSONHelper.serializar(enviarOrdenesRequest);
		return request;
	}

	@Override
	protected EnviarOrdenesResponse responseText(String json) {
		// TODO Auto-generated method stub
		EnviarOrdenesResponse enviarOrdenesResponse = JSONHelper.desSerializar(json,EnviarOrdenesResponse.class);
		return enviarOrdenesResponse;
	}

}
