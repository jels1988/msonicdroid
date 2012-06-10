package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ProfitHistoryDetalleRequest;
import pe.lindley.mmil.titanium.ws.bean.ProfitHistoryDetalleResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ProfitHistoryDetalleProxy extends ProxyBase<ProfitHistoryDetalleResponse> {

	public static final int PROFIT_DATOS_COMERCIALES=0; 
	public static final int PROFIT_DATOS_AVANCERESUMEN=1; 
	
	@InjectResource(R.string.urlwsProfit)protected String urlWS;

	public String codigo;
	public int tipo;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		if(tipo==PROFIT_DATOS_COMERCIALES){
			return urlWS + "/ProfitResumen";
		}else{
			return urlWS + "/ProfitDataAdicional";
		}
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitHistoryDetalleRequest profitHistoryDetalleRequest = new ProfitHistoryDetalleRequest();
		profitHistoryDetalleRequest.codigoCliente = this.codigo;
		String request = JSONHelper.serializar(profitHistoryDetalleRequest);
		return request;
	}

	@Override
	protected ProfitHistoryDetalleResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryDetalleResponse response = JSONHelper.desSerializar(json,ProfitHistoryDetalleResponse.class);
		return response;
	}

}
