package pe.lindley.profit.ws.service;

import pe.lindley.profit.ws.bean.ProfitHistoryDetalleRequest;
import pe.lindley.profit.ws.bean.ProfitHistoryDetalleResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitHistoryDetalleProxy extends ProxyBase<ProfitHistoryDetalleResponse> {

	public static final int PROFIT_DATOS_COMERCIALES=0; 
	public static final int PROFIT_DATOS_AVANCERESUMEN=1; 
	
	@InjectResource(pe.lindley.activity.R.string.urlwsProfit)protected String urlWS;
	
	
	private String codigo;
	private int tipo;
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
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
		profitHistoryDetalleRequest.setCodigoCliente(this.codigo);
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
