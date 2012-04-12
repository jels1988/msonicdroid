package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class UpdateInventarioTO {

	@SerializedName("CPRO")
	public String codigoProducto;	


	@SerializedName("CNRCM")
	public String concrecionActual;	
	
		
	@SerializedName("SOVI")
	public String sovi;

	@SerializedName("SOVICM")
	public String soviActual;
		
		
	@SerializedName("RPRE")
	public String cumplePrecio;
	
	@SerializedName("RPRECM")
	public String cumplePrecioActual;
	

	@SerializedName("NSBCM")
	public int numeroSaboresActual;
	
	
	@SerializedName("ACTR")
	public String codigoAccionTrade;
	
	@SerializedName("DACTR")
	public String descAccionTrade;

	
	@SerializedName("FECC")
	public String fechaCompromiso;
		
}
