package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class UpdateSKUPresentacionTO {

	@SerializedName("CSKU")
	public String codigoSKU;
	        
	@SerializedName("CMP")
	public String compromiso;

	@SerializedName("CNF")
	public String confirmacion;
}
