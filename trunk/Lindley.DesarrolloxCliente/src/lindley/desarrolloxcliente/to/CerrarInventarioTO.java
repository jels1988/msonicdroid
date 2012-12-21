package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CerrarInventarioTO {

	@Expose()
	@SerializedName("CPRO")
	public String codigoProducto;	
	
	@Expose()
	@SerializedName("CNCUM")
	public String concrecionCumplio;
	
	@Expose()
	@SerializedName("SVCUM")
	public String soviCumplio;
	
	@Expose()
	@SerializedName("PRECUM")
	public String cumplePrecioCumplio;
	
	@Expose()
	@SerializedName("NSBCUM")
	public String numeroSaboresCumplio;
	
}
