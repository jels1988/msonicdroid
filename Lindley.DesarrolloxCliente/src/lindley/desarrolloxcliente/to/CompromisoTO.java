package lindley.desarrolloxcliente.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CompromisoTO {

	@SerializedName("PSS")
	public String puntosSugeridos;
	
	@SerializedName("PSG")
	public String puntosGanados;
	
	@SerializedName("CPRO")
	public String codigoProducto;

	@SerializedName("DPRO")
	public String descripcionProducto;
	
	

	@SerializedName("CNR") 
	public String concrecion;

	@SerializedName("CNRCM")
	public String concrecionActual;
	
	@SerializedName("CNCUM")
	public String concrecionCumplio;
	
	
		
	@SerializedName("SOVI")
	public String sovi;

	@SerializedName("SOVICM")
	public String soviActual;
	
	@SerializedName("SVCUM")
	public String soviCumplio;
	
	
		
	@SerializedName("RPRE")
	public String cumplePrecio;
	
	@SerializedName("RPRECM")
	public String cumplePrecioActual;
	
	@SerializedName("PRECUM")
	public String cumplePrecioCumplio;
	
	


	@SerializedName("NSB")
	public String numeroSabores;

	@SerializedName("NSBCM")
	public int numeroSaboresActual;
	
	@SerializedName("NSBCUM")
	public String numeroSaboresCumplio;

	
	
	
	@SerializedName("ACTR")
	public List<AccionTradeTO> listaAccionesTrade;

	
	@SerializedName("FECC")
	public String fechaCompromiso;
	
	public String codigoAccionTrade;
	public String descAccionTrade;
			
}
