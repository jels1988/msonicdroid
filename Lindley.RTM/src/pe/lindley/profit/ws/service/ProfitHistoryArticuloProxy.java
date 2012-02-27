package pe.lindley.profit.ws.service;

import java.util.List;
import pe.lindley.profit.to.Articulo;
import pe.lindley.profit.ws.bean.ProfitHistoryArticuloRequest;
import pe.lindley.profit.ws.bean.ProfitHistoryNewResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitHistoryArticuloProxy extends ProxyBase<ProfitHistoryNewResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlswsProfitNew)protected String urlWS;
	
	private String codigoCliente;
	private int anio;
	private List<Articulo> listArticulo;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public List<Articulo> getListArticulo() {
		return listArticulo;
	}

	public void setListArticulo(List<Articulo> listArticulo) {
		this.listArticulo = listArticulo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ProfitHistoryArticulo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitHistoryArticuloRequest profitHistoryArticuloRequest = new ProfitHistoryArticuloRequest();
		profitHistoryArticuloRequest.setAnio(this.anio);
		profitHistoryArticuloRequest.setCodigoCliente(this.codigoCliente);
		profitHistoryArticuloRequest.setListArticulo(this.listArticulo);
		String request = JSONHelper.serializar(profitHistoryArticuloRequest);
		return request;
	}

	@Override
	protected ProfitHistoryNewResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryNewResponse profitHistoryNewResponse = JSONHelper.desSerializar(json, ProfitHistoryNewResponse.class);
		return profitHistoryNewResponse; 
	}
		        
}
