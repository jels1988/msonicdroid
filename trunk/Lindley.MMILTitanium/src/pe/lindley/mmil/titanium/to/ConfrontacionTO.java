package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class ConfrontacionTO {

	@SerializedName("DES")
	public String descripcion;
	
	@SerializedName("MON")
	public String monto;
	
	@SerializedName("SUP")
	public String supervisor;
	
}
