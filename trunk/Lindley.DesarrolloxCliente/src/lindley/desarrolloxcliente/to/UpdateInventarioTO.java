package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateInventarioTO {

	@Expose()
	@SerializedName("CPRO")
	public String codigoProducto;	

	@Expose()
	@SerializedName("CNRCM")
	public String concrecionActual;	
	
	@Expose()	
	@SerializedName("SOVI")
	public String sovi;

	@Expose()
	@SerializedName("SOVICM")
	public String soviActual;
		
	@Expose()
	@SerializedName("RPRE")
	public String cumplePrecio;
	
	@Expose()
	@SerializedName("RPRECM")
	public String cumplePrecioActual;
	

	@Expose()
	@SerializedName("NSBCM")
	public int numeroSaboresActual;
	
	@Expose()
	@SerializedName("ACTR")
	public String codigoAccionTrade;
	
	@Expose()
	@SerializedName("DACTR")
	public String descAccionTrade;

	@Expose()
	@SerializedName("FECC")
	public String fechaCompromiso;
		
}
