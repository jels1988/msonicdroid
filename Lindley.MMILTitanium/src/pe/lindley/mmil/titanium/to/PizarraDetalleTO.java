package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class PizarraDetalleTO {


	@SerializedName("IND")
	public String indicador;

	@SerializedName("VRE")
	public double valorReal;
	
	@SerializedName("VES")
	public String valorEsperado;
	
	@SerializedName("COL")
	public String color;
}
