package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CerrarSKUPresentacionTO {

	@Expose()
	@SerializedName("CSKU")
	public String codigoSKU;
	        
	@Expose()
	@SerializedName("CUM")
	public String cumplio;
	
}
