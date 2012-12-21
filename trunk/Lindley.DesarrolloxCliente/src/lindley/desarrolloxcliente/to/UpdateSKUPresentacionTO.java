package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSKUPresentacionTO {

	@Expose()
	@SerializedName("CSKU")
	public String codigoSKU;
	        
	@Expose()
	@SerializedName("CMP")
	public String compromiso;
}
