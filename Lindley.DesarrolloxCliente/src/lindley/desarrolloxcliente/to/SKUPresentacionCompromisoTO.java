package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SKUPresentacionCompromisoTO {

	public SKUPresentacionCompromisoTO()
	{
		cumplio = "N";
	}
	
	@Expose()
	@SerializedName("CSKU")
	public String codigoSKU;
	
	@Expose()
	@SerializedName("DSKU")
	public String descripcionSKU;

	@Expose()
	@SerializedName("ACT")
	public String actual;

	@Expose()
	@SerializedName("CMP")
	public String compromiso;
	
	public String cumplio;

}
