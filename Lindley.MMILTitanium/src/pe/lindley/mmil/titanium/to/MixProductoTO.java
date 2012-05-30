package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class MixProductoTO {

	@SerializedName("PRO")
	public String producto;
	
	@SerializedName("CUN")
	public String cajasUnitarias;
	
	@SerializedName("CFI")
	public String cajasFisicas;
}
