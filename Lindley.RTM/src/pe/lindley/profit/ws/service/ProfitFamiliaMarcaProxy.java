package pe.lindley.profit.ws.service;

import java.util.ArrayList;
import pe.lindley.profit.to.Marca;
import pe.lindley.profit.ws.bean.ProfitFamiliaMarcaRequest;
import pe.lindley.profit.ws.bean.ProfitHistoryNewResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitFamiliaMarcaProxy extends ProxyBase<ProfitHistoryNewResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlswsProfitNew)protected String urlWS;
	
	private String codigoCliente;
	private int anio;
	private String familia;
	private ArrayList<Marca> listMarca;

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

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public ArrayList<Marca> getListMarca() {
		return listMarca;
	}

	public void setListMarca(ArrayList<Marca> listMarca) {
		this.listMarca = listMarca;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ProfitHistoryFamiliaMarca";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitFamiliaMarcaRequest profitFamiliaMarcaRequest = new ProfitFamiliaMarcaRequest();
		profitFamiliaMarcaRequest.setAnio(this.anio);
		profitFamiliaMarcaRequest.setCodigoCliente(this.codigoCliente);
		profitFamiliaMarcaRequest.setFamilia(this.familia);
		profitFamiliaMarcaRequest.setListMarca(this.listMarca);
		String request = JSONHelper.serializar(profitFamiliaMarcaRequest);
		return request;
	}

	@Override
	protected ProfitHistoryNewResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryNewResponse profitHistoryNewResponse = JSONHelper.desSerializar(json, ProfitHistoryNewResponse.class);
		return profitHistoryNewResponse; 
	}

}