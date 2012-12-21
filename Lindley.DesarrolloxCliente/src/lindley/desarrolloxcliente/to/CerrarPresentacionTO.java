package lindley.desarrolloxcliente.to;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CerrarPresentacionTO {

	@Expose()
	@SerializedName("CVAR")
	public String codigoVariable;       

	@Expose()
	@SerializedName("LSKU")
	public List<CerrarSKUPresentacionTO> listaSKU;
	
	@Expose()
	@SerializedName("CUM")
	public String cumplio;   
}
