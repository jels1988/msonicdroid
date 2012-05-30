package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class ResumenVentaTO {

	@SerializedName("DES")
	public int descripcion;

	@SerializedName("VAL")
	public int valor;
	
	@SerializedName("IND")
	public int indicador;
	
}
