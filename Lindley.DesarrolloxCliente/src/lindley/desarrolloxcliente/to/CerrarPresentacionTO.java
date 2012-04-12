package lindley.desarrolloxcliente.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CerrarPresentacionTO {

	@SerializedName("CVAR")
	public String codigoVariable;       

	@SerializedName("LSKU")
	public List<CerrarSKUPresentacionTO> listaSKU;
	
	@SerializedName("CUM")
	public String cumplio;   
}
