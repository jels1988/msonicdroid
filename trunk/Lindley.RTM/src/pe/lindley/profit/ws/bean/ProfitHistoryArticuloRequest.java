package pe.lindley.profit.ws.bean;

import java.util.List;

import pe.lindley.profit.to.Articulo;

import com.google.gson.annotations.SerializedName;

public class ProfitHistoryArticuloRequest {

	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("ANIO")
	private int anio;
	
	@SerializedName("ARTS")
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
		        
}

