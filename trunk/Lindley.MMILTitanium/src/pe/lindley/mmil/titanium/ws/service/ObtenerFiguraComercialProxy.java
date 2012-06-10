package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ObtenerFiguraComercialRequest;
import pe.lindley.mmil.titanium.ws.bean.ObtenerFiguraComercialResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ObtenerFiguraComercialProxy extends ProxyBase<ObtenerFiguraComercialResponse> {

	
	@InjectResource(R.string.urlwsFichaFigComercial)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/obtenerFiguraComercial";
	}

	
	public String codigoCliente;
	
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerFiguraComercialRequest obtenerFiguraComercialRequest = new ObtenerFiguraComercialRequest();
		obtenerFiguraComercialRequest.codigo=codigoCliente;
		String request = JSONHelper.serializar(obtenerFiguraComercialRequest);
		return request;
		
	}

	@Override
	protected ObtenerFiguraComercialResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerFiguraComercialResponse obtenerFiguraComercialResponse = JSONHelper.desSerializar(json, ObtenerFiguraComercialResponse.class);
		return obtenerFiguraComercialResponse;
	}

}
