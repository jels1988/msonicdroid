package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class ProfitTO {

	@SerializedName("COD")
	public String nombreIndicador;
	
	@SerializedName("CJF")	       
	public double cajasFisica;
	
	@SerializedName("MAC")
	public double margenActual;

	@SerializedName("CFF")
	public double cajasFisicasFaltante;

	@SerializedName("MGF")
	public double margenFaltante;
}
