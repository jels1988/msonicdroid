package pe.lindley.mmil.titanium.ws.service;

import pe.lindley.mmil.titanium.R;
import pe.lindley.mmil.titanium.ws.bean.ProfitHistoryRequest;
import pe.lindley.mmil.titanium.ws.bean.ProfitHistoryResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ProfitHistoryProxy extends ProxyBase<ProfitHistoryResponse> {

	
	public static final int PROFIT_SIMPLE=0; 
	public static final int PROFIT_COMPARATIVO=1; 
	public static final int PROFIT_SEMANAL=2; 
	
	@InjectResource(R.string.urlwsProfit)protected String urlWS;
	
	public String codigo;
	public int anio;
	public int tipo;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		if(tipo==PROFIT_SIMPLE){
			return urlWS + "/ProfitDetalle";
		}else if(tipo==PROFIT_COMPARATIVO){
			return urlWS + "/ProfitComparativo";
		}else{
			return urlWS + "/ProfitSemanal";
		}
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitHistoryRequest profitHistoryRequest = new ProfitHistoryRequest();
		profitHistoryRequest.anio = anio;
		profitHistoryRequest.codigoCliente=codigo;
		String request = JSONHelper.serializar(profitHistoryRequest);
		return request;
	}

	@Override
	protected ProfitHistoryResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryResponse response = JSONHelper.desSerializar(json,ProfitHistoryResponse.class);
		return response;
	}

}
