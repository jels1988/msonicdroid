package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class InventarioPrecioSugeridoTO {

	@SerializedName("CAN")
	private int cantidad;

	@SerializedName("MAR")
	private String marca;

	@SerializedName("RES")
	private String resultado;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	        
}
