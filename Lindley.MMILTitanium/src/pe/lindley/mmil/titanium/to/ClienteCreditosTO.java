package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class ClienteCreditosTO {

	
	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("CLI")
	public String cliente;
	
	@SerializedName("DIS")
	public String disponible;
	

	@SerializedName("VEN")
	public String vencido;
	
}
