package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class CerrarInventarioTO {

	@SerializedName("CPRO")
	public String codigoProducto;	
	
	@SerializedName("CNCUM")
	public String concrecionCumplio;
	
	@SerializedName("SVCUM")
	public String soviCumplio;
	
	@SerializedName("PRECUM")
	public String cumplePrecioCumplio;
	
	@SerializedName("NSBCUM")
	public String numeroSaboresCumplio;
	
}
