package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class InventarioPrecioMarcadoTO {

	@SerializedName("ICP")
	private int idCompania;
	
	@SerializedName("CMP")
	private String compania;
	
	@SerializedName("RES")
	private String resultado;
	
	@SerializedName("CAN")
	private int cantidad;

	public int getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}