package lindley.desarrolloxcliente.to;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompromisoTO {
	
	public CompromisoTO()
	{
		concrecionCumplio = soviCumplio = cumplePrecioCumplio = numeroSaboresCumplio = "N"; 
	}

	@Expose()
	@SerializedName("PSS")
	public String puntosSugeridos;
	
	@Expose()
	@SerializedName("PSG")
	public String puntosGanados;
	
	@Expose()
	@SerializedName("CPRO")
	public String codigoProducto;

	@Expose()
	@SerializedName("DPRO")
	public String descripcionProducto;
	
	
	@Expose()
	@SerializedName("CNR") 
	public String concrecion;

	@Expose()
	@SerializedName("CNRCM")
	public String concrecionActual;
	
	@Expose()
	@SerializedName("CNCUM")
	public String concrecionCumplio;
	
	
	@Expose()
	@SerializedName("SOVI")
	public String sovi;

	@Expose()
	@SerializedName("SOVICM")
	public String soviActual;
	
	@Expose()
	@SerializedName("SVCUM")
	public String soviCumplio;
	
	
	@Expose()
	@SerializedName("RPRE")
	public String cumplePrecio;
	
	@Expose()
	@SerializedName("RPRECM")
	public String cumplePrecioActual;
	
	@Expose()
	@SerializedName("PRECUM")
	public String cumplePrecioCumplio;
	
	

	@Expose()
	@SerializedName("NSB")
	public String numeroSabores;

	@Expose()
	@SerializedName("NSBCM")
	public int numeroSaboresActual;
	
	@Expose()
	@SerializedName("NSBCUM")
	public String numeroSaboresCumplio;

	
	
	@Expose()
	@SerializedName("ACTR")
	public List<AccionTradeTO> listaAccionesTrade;

	@Expose()
	@SerializedName("FECC")
	public String fechaCompromiso;
	
	public String codigoAccionTrade;
	
	@Expose()
	@SerializedName("DSACTR")
	public String descAccionTrade;
	
	@Expose()
	@SerializedName("CDLEG")
	public String codigoLegacy;
			
}
