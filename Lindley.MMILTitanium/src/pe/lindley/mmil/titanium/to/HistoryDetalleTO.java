package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class HistoryDetalleTO {

	
	
	@SerializedName("nuanio")
	public int anio;
	
	@SerializedName("numes")
	public int mes;
	
	@SerializedName("sem")
	public int semana;
	
	@SerializedName("cacfa")
	public double cajasFacturadas;
	
	@SerializedName("caufa")
	public double cajasUnitarias;
	
	@SerializedName("imfac")
	public double importeFacturado;
	
	@SerializedName("imuti")
	public double utilidad;
	
	
}
