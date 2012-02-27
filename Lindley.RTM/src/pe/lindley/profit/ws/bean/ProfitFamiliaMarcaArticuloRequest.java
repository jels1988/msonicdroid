package pe.lindley.profit.ws.bean;

import java.util.List;

import pe.lindley.profit.to.Articulo;

import com.google.gson.annotations.SerializedName;

public class ProfitFamiliaMarcaArticuloRequest {
	
	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("ANIO")
	private int anio;

	@SerializedName("FAM")
	private String familia;

	@SerializedName("MAR")
	private String marca;
		        
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

}
