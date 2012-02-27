package pe.lindley.profit.ws.bean;

import java.util.List;

import pe.lindley.profit.to.Marca;

import com.google.gson.annotations.SerializedName;

public class ProfitFamiliaMarcaRequest {

	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("ANIO")
	private int anio;

	@SerializedName("FAM")
	private String familia;

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

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public List<Marca> getListMarca() {
		return listMarca;
	}

	public void setListMarca(List<Marca> listMarca) {
		this.listMarca = listMarca;
	}
}
