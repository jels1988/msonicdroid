package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.MixProductoResponse;
import pe.lindley.mmil.titanium.ws.bean.ResumenVentaRequest;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class MixProductoProxy extends ProxyBase<MixProductoResponse> {


	public String codigoDeposito;
	public String codigoSupervisor;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/MixProducto";
	}
	
	

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ResumenVentaRequest resumenVentaRequest = new ResumenVentaRequest();
		resumenVentaRequest.codigoDeposito = codigoDeposito;
		resumenVentaRequest.codigoSupervisor = codigoSupervisor;
		
		String request = JSONHelper.serializar(resumenVentaRequest);
		return request;
	}

	@Override
	protected MixProductoResponse responseText(String json) {
		// TODO Auto-generated method stub
		//json = "{\"MPR\":[{\"PRO\":\"SAN LUIS S/G 2.5L PFM*6\",\"CUN\":\"374.90\",\"CFI\":\"142.00\"},{\"PRO\":\"SAN LUIS S/G 625 ML PET*15\",\"CUN\":\"321.84\",\"CFI\":\"195.01\"}],\"STS\":0,\"DES\":null}";
		MixProductoResponse response =  JSONHelper.desSerializar(json,MixProductoResponse.class);
		return response;
	}

}
