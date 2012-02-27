package pe.lindley.profit.ws.service;

import java.util.List;
import pe.lindley.profit.to.Marca;
import pe.lindley.profit.ws.bean.ProfitHistoryNewResponse;
import pe.lindley.profit.ws.bean.ProfitSegmentoMarcaRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ProfitSegmentoMarcaProxy extends ProxyBase<ProfitHistoryNewResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlswsProfitNew)protected String urlWS;
	
	private String codigoCliente;
	private int anio;
	private String segmento;
	private List<Marca> listMarca;

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

	public List<Marca> getListMarca() {
		return listMarca;
	}

	public void setListMarca(List<Marca> listMarca) {
		this.listMarca = listMarca;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ProfitHistorySegmentoMarca";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ProfitSegmentoMarcaRequest profitSegmentoMarcaRequest = new ProfitSegmentoMarcaRequest();
		profitSegmentoMarcaRequest.setAnio(this.anio);
		profitSegmentoMarcaRequest.setCodigoCliente(this.codigoCliente);
		profitSegmentoMarcaRequest.setListMarca(this.listMarca);
		profitSegmentoMarcaRequest.setSegmento(this.segmento);
		String request = JSONHelper.serializar(profitSegmentoMarcaRequest);
		return request;
	}

	@Override
	protected ProfitHistoryNewResponse responseText(String json) {
		// TODO Auto-generated method stub
		ProfitHistoryNewResponse profitHistoryNewResponse = JSONHelper.desSerializar(json, ProfitHistoryNewResponse.class);
		return profitHistoryNewResponse; 
	}
	
}
