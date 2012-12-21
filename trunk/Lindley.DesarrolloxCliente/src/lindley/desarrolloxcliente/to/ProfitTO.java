package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfitTO {

	@Expose()
	@SerializedName("COD")
	public String nombreIndicador;
	
	@Expose()
	@SerializedName("CJF")	       
	public double cajasFisica;
	
	@Expose()
	@SerializedName("MAC")
	public double margenActual;

	@Expose()
	@SerializedName("CFF")
	public double cajasFisicasFaltante;

	@Expose()
	@SerializedName("MGF")
	public double margenFaltante;
}
