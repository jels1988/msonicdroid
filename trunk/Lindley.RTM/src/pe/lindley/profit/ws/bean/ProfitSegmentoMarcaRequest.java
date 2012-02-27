package pe.lindley.profit.ws.bean;

import java.util.List;

import pe.lindley.profit.to.Marca;

import com.google.gson.annotations.SerializedName;

public class ProfitSegmentoMarcaRequest {

	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("ANIO")
	private int anio;

	@SerializedName("SGM")
	private String segmento;

	@SerializedName("MARS")
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
}
