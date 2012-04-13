package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class CerrarPosicionTO {

	@SerializedName("CVAR")
	public String codigoVariable;
	
	@SerializedName("CUM")
	public String cumplio;
	
	@SerializedName("FFIN")
	public String fotoFinal;
	
}
