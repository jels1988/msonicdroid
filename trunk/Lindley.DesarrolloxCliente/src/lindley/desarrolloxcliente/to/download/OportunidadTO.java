package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OportunidadTO {
	public long id;
	
	@Expose()
	@SerializedName("CLI")
	public String codigoCliente;
	
	@Expose()
	@SerializedName("PRO")
	public String codigoProducto;
	
	@Expose()
	@SerializedName("CON")
	public String concrecion;
	
	@Expose()
	@SerializedName("SOV")
	public String sovi;
	
	@Expose()
	@SerializedName("RPR")
	public String respetoPrecios;
	
	@Expose()
	@SerializedName("NSA")
	public String numeroSabores;
	
	@Expose()
	@SerializedName("LEG")
	public String legacy;
	
	@Expose()
	@SerializedName("ORD")
	public int orden;
	

}
