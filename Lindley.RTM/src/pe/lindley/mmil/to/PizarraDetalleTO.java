package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class PizarraDetalleTO {
	
	@SerializedName("IND")
	private String indicador;

	@SerializedName("VRE")
	private double valorReal;
	
	@SerializedName("VES")
	private String valorEsperado;
	
	@SerializedName("COL")
	private String color;

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public double getValorReal() {
		return valorReal;
	}

	public void setValorReal(double valorReal) {
		this.valorReal = valorReal;
	}

	public String getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(String valorEsperado) {
		this.valorEsperado = valorEsperado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
