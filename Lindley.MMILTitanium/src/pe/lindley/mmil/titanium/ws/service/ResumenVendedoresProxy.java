package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ResumenVentaRequest;
import pe.lindley.mmil.titanium.ws.bean.ResumenVentaResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ResumenVendedoresProxy extends ProxyBase<ResumenVentaResponse> {

	
	public static final int RESUMEN_VENTAS=0;
	public static final int RESUMEN_FRANQUICIA=1;
	
	public String codigoDeposito;
	public String codigoSupervisor;
	public int metodoRest;
	
	@InjectResource(R.string.urlwsMMILSupervisor)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		if(metodoRest==0){
			return urlWS + "/ResumenVendedores";
		}else{
			return urlWS + "/ResumenAdminFranquicia";
		}
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
	protected ResumenVentaResponse responseText(String json) {
		// TODO Auto-generated method stub
		
		ResumenVentaResponse response =  JSONHelper.desSerializar(json,ResumenVentaResponse.class);
		return response;
	}

}
