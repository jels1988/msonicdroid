package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class SKUPresentacionCompromisoTO {

	@SerializedName("CSKU")
	public String codigoSKU;
	
	@SerializedName("DSKU")
	public String descripcionSKU;

	@SerializedName("ACT")
	public String actual;

	@SerializedName("CMP")
	public String compromiso;
	
	public String cumplio;

}
