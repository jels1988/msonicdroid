package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class OportunidadTO {
	public long id;
	
	@SerializedName("CLI")
	public String codigoCliente;
	
	@SerializedName("PRO")
	public String codigoProducto;
	
	@SerializedName("CON")
	public String concrecion;
	
	@SerializedName("SOV")
	public String sovi;
	
	@SerializedName("RPR")
	public String respetoPrecios;
	
	@SerializedName("NSA")
	public String numeroSabores;
	
	@SerializedName("LEG")
	public String legacy;
	
	@SerializedName("ORD")
	public int orden;
	

}
