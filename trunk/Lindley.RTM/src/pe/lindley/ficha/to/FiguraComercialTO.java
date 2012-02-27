package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class FiguraComercialTO {
	
	@SerializedName("DES")
	private String descripcion;

	@SerializedName("VAL")
	private String valor;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
