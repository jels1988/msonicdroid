package pe.lindley.profit.ws.service;

import java.util.List;
import pe.lindley.profit.to.Articulo;
import pe.lindley.profit.ws.bean.ProfitFamiliaMarcaArticuloRequest;
import pe.lindley.profit.ws.bean.ProfitHistoryNewResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitFamiliaMarcaArticuloProxy extends ProxyBase<ProfitHistoryNewResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlswsProfitNew)protected String urlWS;
	
	private String codigoCliente;
	private int anio;
	private String familia;
	private String marca;
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

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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
		return urlWS + "/ProfitHistoryFamiliaMarcaArticulo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitFamiliaMarcaArticuloRequest profitFamiliaMarcaArticuloRequest = new ProfitFamiliaMarcaArticuloRequest();
		profitFamiliaMarcaArticuloRequest.setAnio(this.anio);
		profitFamiliaMarcaArticuloRequest.setCodigoCliente(this.codigoCliente);
		profitFamiliaMarcaArticuloRequest.setFamilia(this.familia);
		profitFamiliaMarcaArticuloRequest.setListArticulo(this.listArticulo);
		profitFamiliaMarcaArticuloRequest.setMarca(this.marca);
		String request = JSONHelper.serializar(profitFamiliaMarcaArticuloRequest);
		return request;
	}

	@Override
	protected ProfitHistoryNewResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryNewResponse profitHistoryNewResponse = JSONHelper.desSerializar(json, ProfitHistoryNewResponse.class);
		return profitHistoryNewResponse; 
	}
}
