package pe.lindley.profit.ws.service;

import java.util.List;
import pe.lindley.profit.to.Articulo;
import pe.lindley.profit.ws.bean.ProfitHistoryNewResponse;
import pe.lindley.profit.ws.bean.ProfitSegmentoMarcaArticuloRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitSegmentoMarcaArticuloProxy extends ProxyBase<ProfitHistoryNewResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlswsProfitNew)protected String urlWS;
	
	private String codigoCliente;
	private int anio;
	private String segmento;
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

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
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
		return urlWS + "/ProfitHistorySegmentoMarcaArticulo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitSegmentoMarcaArticuloRequest profitSegmentoMarcaArticuloRequest = new ProfitSegmentoMarcaArticuloRequest();
		profitSegmentoMarcaArticuloRequest.setAnio(this.anio);
		profitSegmentoMarcaArticuloRequest.setCodigoCliente(this.codigoCliente);
		profitSegmentoMarcaArticuloRequest.setListArticulo(this.listArticulo);
		profitSegmentoMarcaArticuloRequest.setMarca(this.marca);
		profitSegmentoMarcaArticuloRequest.setSegmento(this.segmento);
		String request = JSONHelper.serializar(profitSegmentoMarcaArticuloRequest);
		return request;
	}

	@Override
	protected ProfitHistoryNewResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryNewResponse profitHistoryNewResponse = JSONHelper.desSerializar(json, ProfitHistoryNewResponse.class);
		return profitHistoryNewResponse;
	}
}
