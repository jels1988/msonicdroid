package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CerrarPosicionTO {

	@Expose()
	@SerializedName("CVAR")
	public String codigoVariable;
	
	@Expose()
	@SerializedName("CUM")
	public String cumplio;
	
	@Expose()
	@SerializedName("FFIN")
	public String fotoFinal;
	
}
