package pe.lindley.profit.ws.service;

import pe.lindley.profit.ws.bean.ProfitHistoryRequest;
import pe.lindley.profit.ws.bean.ProfitHistoryResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitHistoryProxy extends ProxyBase<ProfitHistoryResponse> {
	
	public static final int PROFIT_SIMPLE=0; 
	public static final int PROFIT_COMPARATIVO=1; 
	public static final int PROFIT_SEMANAL=2; 
	
	@InjectResource(pe.lindley.activity.R.string.urlwsProfit)protected String urlWS;
	
	private String codigo;
	private int anio;
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

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

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
		profitHistoryRequest.setAnio(getAnio());
		profitHistoryRequest.setCodigoCliente(getCodigo());
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
