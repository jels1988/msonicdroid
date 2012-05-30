package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class ResumenVentaTO {

	@SerializedName("DES")
	public String descripcion;

	@SerializedName("VAL")
	public String valor;
	
	@SerializedName("IND")
	public String indicador;
	
}
